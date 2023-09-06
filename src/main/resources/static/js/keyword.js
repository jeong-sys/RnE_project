// /api/getTexts 엔드포인트로 GET 요청 보내기
fetch('/getTexts')
    .then(response => response.json())
    .then(data => {
        // 서버에서 받아온 맵 데이터 사용
        console.log(data);

        // 맵 데이터에 따라 글자 색상 변경
        if (data.day1 == 'day1_1') {
            document.getElementById('day1_1').style.color = 'blue';
        } else if (data.day1 == 'day1_2') {
            document.getElementById('day1_1').style.color = 'blue';
        }

        if (data.day2 == 'day2_1') {
            document.getElementById('day2_1').style.color = 'blue';
        }
    })
    .catch(error => {
        console.error('오류 발생:', error);
    });
