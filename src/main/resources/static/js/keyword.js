// /api/getTexts 엔드포인트로 GET 요청 보내기
fetch('/getCondition')
    .then(response => response.text())
    .then(data => {

        var resultCondition = data;
        console.log('resultCondition :',resultCondition);

        fetch('/getTexts')
            .then(response => response.json())
            .then(data => {
                // 서버에서 받아온 맵 데이터 사용
                console.log(data);
                let total = 0;

                if (resultCondition == "test1_result") {
                    document.getElementById('test1_result').style.display = 'block';
                    // 맵 데이터에 따라 글자 색상 변경
                    if (data.day1.includes('day1_1')) {
                        document.getElementById('test1_day1_1').style.color = 'blue';
                        total += 10;
                    }
                    if (data.day1.includes('day1_2')) {
                        document.getElementById('test1_day1_2').style.color = 'blue';
                        total += 10;
                    }
                    if (data.day2.includes('day2_1')) {
                        document.getElementById('test1_day2_1').style.color = 'blue';
                        total += 10;
                    }

                    // total 점수를 HTML에 업데이트
                    document.getElementById('score').textContent = `Total Score: ${total}`;
                }

                if (resultCondition == "test2_result") {
                    document.getElementById('test2_result').style.display = 'block';
                    // 맵 데이터에 따라 글자 색상 변경
                    if (data.day1.includes('day1_1')) {
                        document.getElementById('test2_day1_1').style.color = 'blue';
                        total += 10;
                    }
                    if (data.day2.includes('day2_1')) {
                        document.getElementById('test2_day2_1').style.color = 'blue';
                        total += 10;
                    }

                    // total 점수를 HTML에 업데이트
                    document.getElementById('score').textContent = `Total Score: ${total}`;
                }

                if (resultCondition == "test3_result") {
                    document.getElementById('test3_result').style.display = 'block';
                    // 맵 데이터에 따라 글자 색상 변경
                    if (data.day1.includes('안녕')) {
                        document.getElementById('test2_day1_1').style.color = 'blue';
                        total += 10;
                    }
                    if (data.day2.includes('day2_1')) {
                        document.getElementById('test2_day2_1').style.color = 'blue';
                        total += 10;
                    }

                    // total 점수를 HTML에 업데이트
                    document.getElementById('score').textContent = `Total Score: ${total}`;
                }

                if (resultCondition == "test4_result") {
                    document.getElementById('test4_result').style.display = 'block';
                    // 맵 데이터에 따라 글자 색상 변경
                    if (data.day1.includes('잘가')) {
                        document.getElementById('test2_day1_1').style.color = 'blue';
                        total += 10;
                    }
                    if (data.day2.includes('day2_1')) {
                        document.getElementById('test2_day2_1').style.color = 'blue';
                        total += 10;
                    }

                    // total 점수를 HTML에 업데이트
                    document.getElementById('score').textContent = `Total Score: ${total}`;
                }
            })
            .catch(error => {
                console.error('오류 발생:', error);
            });

    })
    .catch(error => {
        console.error('오류발생', error);
    });
