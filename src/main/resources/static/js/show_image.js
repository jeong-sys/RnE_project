function showImage(event) {
    event.preventDefault();  // Add this line
    document.getElementById('placeholder2').style.display = 'none';
    document.getElementById('x-image').style.display = 'block';
}


function displayImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            document.getElementById('placeholder1').style.display = 'none';
            document.getElementById('myImage').src = e.target.result;
            document.getElementById('myImage').style.display = 'block';
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function submitFormOrAlert() {
    const imageInput = document.getElementById('image');
    if (imageInput.files.length === 0) {
        alert('이미지를 업로드 해주세요');
    } else {
        imageInput.form.submit();  // 현재 폼을 제출
    }
}



function submitOrAlert() {
    if (confirm('제출하시겠습니까?')) { // 알림창으로 확인
        window.location.href = '/final_page'; // 사용자가 OK를 선택하면 finish.html로 이동
    }
}


