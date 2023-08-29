
// 캐시 이름을 사용하여 저장된 데이터를 조회합니다.
async function getCacheData(cacheName) {
    const cache = await caches.open(cacheName);
    const keys = await cache.keys();

    let data = {};

    for(let key of keys) {
        const response = await cache.match(key);
        if(response) {
            data[key.url] = await response.text();
        }
    }

    return data;
}

// 예제 사용:
getCacheData('page-texts').then(data => {
    console.log(data); // 콘솔에 캐시 데이터 출력
});