import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import Dataset, DataLoader
from torchvision import transforms
from PIL import Image
import os

# 신경망 정의
class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        self.fc1 = nn.Linear(28*84, 500)
        self.fc2 = nn.Linear(500, 10)

    def forward(self, x):
        x = x.view(-1, 28*84)
        x = torch.relu(self.fc1(x))
        x = self.fc2(x)
        return x


# 사용자 정의 데이터셋
class CustomDataset(Dataset):
    def __init__(self, images, transform=None):
        self.transform = transform
        self.data = images

    def __len__(self):
        return len(self.data)

    def __getitem__(self, index):
        img, label = self.data[index]
        if self.transform:
            img = self.transform(img)
        return img, label

transform = transforms.Compose([
    transforms.Resize((28, 28)),
    transforms.ToTensor()
])

data = []
image_dir = 'src/main/resources/static/img/learning/'  # 이미지가 저장된 디렉토리 경로

for file_name in os.listdir(image_dir):
    if file_name.endswith('.jpeg'):
        image_path = os.path.join(image_dir, file_name)
        image = Image.open(image_path)

        # for i in range(9): # 9개의 숫자에 대해서
        #     for j in range(13): # 각 숫자별 6개의 스타일
        #         cropped_img = image.crop((j*28, i*28, (j+1)*28, (i+1)*28))
        #         data.append((cropped_img, i))
        for i in range(9): # 9개의 숫자에 대해서
            for j in range(13): # 각 숫자별 6개의 스타일
                cropped_img = image.crop((j*28, i*28, (j+1)*28, (i+1)*28))
                data.append((cropped_img, i))


dataset = CustomDataset(data, transform=transform)
dataloader = DataLoader(dataset, batch_size=32, shuffle=True)


print("Total data:", len(data))
for imgs, labels in dataloader:
    print("Batch size:", len(imgs))

# 모델, 손실 함수, 최적화 알고리즘 설정
model = Net()
criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

# 학습
epochs = 5
for epoch in range(epochs):
    for imgs, labels in dataloader:
        optimizer.zero_grad()
        outputs = model(imgs)
        loss = criterion(outputs, labels)
        loss.backward()
        optimizer.step()
    print(f"Epoch {epoch+1}/{epochs}, Loss: {loss.item():.4f}")

# 모델 저장
model_save_path = 'src/main/python/model_pth/model.pth'
torch.save(model.state_dict(), model_save_path)
print(f"Model saved to {model_save_path}")