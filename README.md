## 데이터

데이터는 유튜브의 싫어요 수와 조회수 와의 관계로 정했고 원래 구하려 하는 회귀식에서 nextGaussian 메소드로 오차를 만들어서 다음과 같이 구했습니다.

[참고 : 조회수와 좋아요 수의 상관관계](https://philosophiren.com/311)

```java
public static int[][] randomgap(){
        Random r = new Random();
        int[][] ex = new int [20][2];
        for (int i = 0; i < 20; i++) {
            ex[i][0] = i;
            ex[i][1] = i * 2000 + (int)(100 * r.nextGaussian());
        }
        return ex;
    }
```

## 결과물

![예제  결과](https://user-images.githubusercontent.com/63089645/85693541-4c42e180-b711-11ea-836a-39c499215785.png)

 코드 결과물입니다. 첫줄부터 스무번째 줄까지 사용한 데이터고 마지막 줄 은 회귀식 입니다.

이를 기반으로 그래프를 그리면 다음과 같습니다.

![컴알 삽입용 그래프](https://user-images.githubusercontent.com/63089645/85693230-07b74600-b711-11ea-865f-3be757790361.png)

