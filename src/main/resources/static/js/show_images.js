let currentPage = 1; // 현재 페이지 번호
let currentTable = null; // 현재 선택된 테이블

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

    // getElementById : 아이디 요소 선택
    document.getElementById('selection').style.display = 'none'; // 선택창 숨김
    document.getElementById('imageDisplay').style.display = 'block'; // 이미지 페이지 표시
    loadImages(); // 이미지 로드 함수 호출
}

// 이미지를 로드하는 함수
function loadImages() {
    fetch(`/getImages?page=${currentPage}&condition=${currentTable}`) // 서버에 이미지 데이터 요청
        .then(response => response.json()) // 응답을 JSON 형식으로 파싱
        .then(data => { // 데이터 처리
            if (data.length == 0) { // 이미지 데이터가 없으면
                window.location.href = "ImageFinish"; // 다른 페이지로 리다이렉트
            }
            displayImages(data); // 이미지 표시 함수 호출
        });
}

// 받아온 이미지 데이터를 표시하는 함수
function displayImages(data) {

    const image1Data = "data:image/jpeg;base64," + data[0];
    const image2Data = data[1] ? "data:image/jpeg;base64," + data[1] : '';

    document.getElementById('image1').src = image1Data; // 첫 번째 이미지 표시
    document.getElementById('image2').src = image2Data; // 두 번째 이미지 표시 (있으면)
}

function updatePageNum(){
    document.getElementById('page').innerText = `Page ${currentPage}`;
}

// Cache에 현재 페이지의 텍스트를 저장하는 함수
async function saveCurrentTextToCache() {
    const textInput = document.querySelector('.text');
    const textValue = textInput.value;

    // Cache API를 사용해 값을 저장
    const cache = await caches.open('page-texts');
    const response = new Response(new Blob([textValue], { type: 'text/plain' }));
    await cache.put(`text-page-${currentPage}`, response);
}

// 현재 페이지에 해당하는 텍스트가 캐시에 있다면 불러와 표시
async function loadTextFromCache() {
    const cache = await caches.open('page-texts');
    const response = await cache.match(`text-page-${currentPage}`);
    const textInput = document.querySelector('.text');

    if (response) {
        const textValue = await response.text();
        textInput.value = textValue;
    } else {
        textInput.value = '';
    }
}

// 페이지 이동하는 함수 (이전/다음 버튼 클릭 시)
async function changePage(offset) {
    await saveCurrentTextToCache();
    currentPage += offset;
    updatePageNum();
    await loadImages();
    await loadTextFromCache();
}

// ... 기존 코드 ...

// 페이지 문자 변화

