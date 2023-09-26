let currentPage = 1; // 현재 페이지 번호
let currentTable = null; // 현재 선택된 테이블
let finalPage = 5;
let newPage;

// 사용자가 선택한 테이블에 따라 이미지를 로드하는 함수
function submitSelection() {

    // Document 객체 : 웹 페이지 그 자체(웹 페이지에 존재하는 html 요소에 접근)
    const radios = document.querySelectorAll('input[name="table"]'); // 라디오 버튼을 모두 가져옴
    for (let radio of radios) { // 각 라디오 버튼에 대하여
        if (radio.checked) { // 선택된 버튼이면
            currentTable = radio.value; // 해당 테이블 값을 저장하고
            break; // 반복 종료
        }
    }
    if (!currentTable) { // 테이블이 선택되지 않았으면
        alert("조건을 선택해주세요!"); // 알림 메시지 표시
        return; // 함수 종료
    }

    // 캐시 삭제
    caches.keys().then(function(keyList) {
        return Promise.all(keyList.map(function(key) {
            return caches.delete(key);
        }));
    });

    // getElementById : 아이디 요소 선택
    document.getElementById('selection').style.display = 'none'; // 선택창 숨김
    document.getElementById('imageDisplay').style.display = 'block'; // 이미지 페이지 표시
    loadImages(); // 이미지 로드 함수 호출
}

// 이미지를 로드하는 함수
async function loadImages() {

    const response = await fetch(`/getImages?page=${currentPage}&condition=${currentTable}`); // fetch 요청
    const data = await response.json(); // 응답을 JSON 형식으로 파싱

    btnChange();
    displayImages(data);
}

// 마지막 페이지 버튼 submit 변경
function btnChange() {
    if (currentPage == finalPage)
    { document.getElementById('write_submit').innerText = `Submit`; }
    else { document.getElementById('write_submit').innerText = 'Next'}
}

// 받아온 이미지 데이터를 표시하는 함수
function displayImages(data) {

    const image1Data = "data:image/jpeg;base64," + data[0];
    const image2Data = data[1] ? "data:image/jpeg;base64," + data[1] : '';

    document.getElementById('image1').src = image1Data; // 첫 번째 이미지 표시
    document.getElementById('image2').src = image2Data; // 두 번째 이미지 표시 (있으면)
}

function updatePageNum(){
    document.getElementById('page').innerText = `Day ${currentPage}`;
}

// Cache에 현재 페이지의 텍스트를 저장하는 함수
async function saveCurrentTextToCache() {
    const textInput = document.querySelector('.text');
    const textValue = textInput.value;

    // Cache API를 사용해 값을 저장
    const cache = await caches.open('page-texts');
    const response = new Response(new Blob([textValue], { type: 'text/plain;charset=utf-8' }));
    await cache.put(`text-page-${currentPage}`, response);
}

// 현재 페이지에 해당하는 텍스트가 캐시에 있다면 불러와 표시
async function loadTextFromCache() {
    const cache = await caches.open('page-texts');
    const response = await caches.match(`text-page-${currentPage}`);
    const textInput = document.querySelector('.text');

    if (response) {
        const textValue= await response.text();
        textInput.value = textValue;
    } else {
        textInput.value = '';
    }
}

document.addEventListener("DOMContentLoaded", function (){
    btnHidden();
});
function btnHidden() {

    // 왜 처음부터 안나옴.....

    if (newPage > 1)
    { document.getElementById('previous_btn').style.display = 'block' ; }
    else { document.getElementById('previous_btn').style.display = 'none'; }

}

// 페이지 이동하는 함수 (이전/다음 버튼 클릭 시)
async function changePage(offset) {
    await saveCurrentTextToCache();

    newPage = currentPage + offset; // 새로운 페이지 계산
    btnHidden();

    // 이미지 로드 시도
    const response = await fetch(`/getImages?page=${newPage}&condition=${currentTable}`);
    const data = await response.json();

    // 이미지 데이터가 있을 경우만 페이지를 업데이트
    if (data.length > 0) {;
        currentPage = newPage; // 페이지 번호 업데이트
        btnChange();
        updatePageNum();
        displayImages(data);
        await loadTextFromCache();
    } else {
        if (confirm("저장하고 점수를 확인하시겠습니까?")) {
            await sendDataToServer();
            window.location.href = "score_check";
        }
    }
}

// 모든 캐시된 데이터를 불러와 서버로 전송
async function sendDataToServer() {
    const cacheData = await fetchDataFromCache('page-texts');
    console.log("Sending data: ", cacheData);

    // 데이터를 서버에 전송합니다.
    fetch('/api/saveCacheToDB', { // fetch API 로 '/saveCache'경로에 POST 요청
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // json 형태 전송
        },
        body: JSON.stringify(cacheData) // 문자열 형태의 json 전송
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        });
}

// 모든 캐시된 데이터를 불러오는 함수
async function fetchDataFromCache(cacheName) {
    const cache = await caches.open(cacheName);
    const keys = await cache.keys(); // 해당 캐시의 모든 키(URL 가져옴)

    let cacheData = [];

    for (let request of keys) {
        const response = await cache.match(request);
        const data = await response.text();

        cacheData.push({
            key: request.url,
            value: data
        });
    }

    return cacheData;
}






