# 환경 변수 설정
import os
os.environ['MKL_CBWR'] = 'AUTO'
os.environ['MKL_SERVICE_FORCE_INTEL'] = '1'

import warnings
warnings.filterwarnings("ignore", category=UserWarning, module="torch")

import torch
import torch.nn as nn
from torchvision import transforms
from PIL import Image


class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.fc1 = nn.Linear(28*84, 500)  # 변경: 28*84
        self.fc2 = nn.Linear(500, 10)

    def forward(self, x):
        x = x.view(-1, 28*84)  # 변경: 28*84
        x = torch.relu(self.fc1(x))
        x = self.fc2(x)
        return x

transform = transforms.Compose([
    transforms.Resize((28, 84)),  # 변경: 28x84 크기로 변경
    transforms.ToTensor()
])

def predict_image(image_path, model_path):
    # 이미지 불러오기 및 전처리
    image = Image.open(image_path).convert('L')  # 그레이스케일로 변환
    image = transform(image).unsqueeze(0)  # 차원 추가

    # 모델 불러오기
    model = Net()
    model.load_state_dict(torch.load(model_path, map_location=torch.device('cpu')))
    model.eval()  # 평가 모드

    # 예측
    with torch.no_grad():
        outputs = model(image)
        _, predicted = torch.max(outputs.data, 1)

    return predicted.item()

if __name__ == "__main__":
    directory_path = 'src/main/resources/static/predictImg/'  # 예측하고자 하는 이미지들이 있는 디렉토리의 경로
    image_filenames = [f for f in os.listdir(directory_path) if os.path.isfile(os.path.join(directory_path, f)) and f.lower().endswith(('png', 'jpg', 'jpeg'))]

    # 디렉토리 내의 첫 번째 이미지 파일을 가져옵니다.
    if image_filenames:
        image_path = os.path.join(directory_path, image_filenames[0])
    else:
        raise ValueError("No image files found in the specified directory.")

    model_path = 'src/main/python/model_pth/model.pth'  # 모델이 저장된 경로
    result = predict_image(image_path, model_path)
    try:
        result = predict_image(image_path, model_path)
        print(f"Predicted number: {result}")
    except Exception as e:
        print(f"Error occurred: {e}")

