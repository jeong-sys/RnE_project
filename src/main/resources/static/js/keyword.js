// /api/getTexts 엔드포인트로 GET 요청 보내기
fetch('/getTexts')
    .then(response => response.json())
    .then(data => {
        // 서버에서 받아온 맵 데이터 사용
        console.log(data);
        let total = 0;

        // 맵 데이터에 따라 글자 색상 변경
        if (data.day1 == 'day1_1') {
            document.getElementById('day1_1').style.color = 'blue';
            total = total + 10;
        } else if (data.day1 == 'day1_2') {
            document.getElementById('day1_1').style.color = 'blue';
            total = total + 10;
        }

        if (data.day2 == 'day2_1') {
            document.getElementById('day2_1').style.color = 'blue';
            total = total + 10;
        }

        // total 점수를 HTML에 업데이트
        document.getElementById('score').textContent = `Total Score: ${total}`;

    })
    .catch(error => {
        console.error('오류 발생:', error);
    });

