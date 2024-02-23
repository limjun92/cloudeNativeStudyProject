 # 클라우드 네이티브 아키텍처(IaaS)
## 클라우드 아키텍처 설계
### 클라우드 아키텍처 구성, MSA 아키텍처 구성도

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.png" width="500">

# 클라우드 네이비트 모델링(Biz.)
## Data Modeling/서비스 분리/설계 역량
### 도메일분석 - 이벤트 스토밍

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/%EC%9D%B4%EB%B2%A4%ED%8A%B8%EC%8A%A4%ED%86%A0%EB%B0%8D%202024-02-22%20203416.png" width="700">

# 클라우드 네이티브 개발 MSA(Dev.)
## MSA 개발 또는 개발관리 역량
### 분산 트랜잭션 - Saga

* 흐름
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/%EC%82%AC%EA%B0%80%EB%8F%99%EC%9E%912.png" width="600">

* Trade service
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/buyService.png" width="700">

* Trade aggregate
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/buyAggregate.png" width="450">

* Trade saga
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-02-22%20212142.png" width="800">

* Wallet aggregate
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/walletAggregate.png" width="500">

### 보상 처리 - Compensation

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/%EB%B3%B4%EC%83%81%EA%B4%80%EB%A0%A8.png" width="700">

### 단일 진입점 - Gateway

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/%EA%B2%8C%EC%9D%B4%ED%8A%B8%EC%9B%A8%EC%9D%B4%202024-02-22%20221104.png" width="700">
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/8080.png" width="400">
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/8084.png" width="400">

### 분산 데이터 프로젝션 - CQRS

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/CQRS.png" width="400">

# 클라우드 네이티브 운영(PaaS)
## 클라우드 배포 역량
### 클라우드 배포 -Container 운영

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/2.png" width="400">
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/5.png" width="400">
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/14.png" width="700">

## 컨테이너 인프라 설계 및 구성 역량
### 컨테이너 자동확정 - HPA

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/3.png" width="300">
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/1.png" width="400">

### 컨테이너로부터 환경분리 - CofigMap/Secret

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/15.png" width="700">
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/16.png" width="200">

### 클라우드스토리지 활용 - PVC
### 셀프 힐링/무정지배포 - Liveness/Rediness Probe

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/12.png" width="400">
<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/11.png" width="700">

### 서비스 메쉬 응용 - Mesh
### 통합 모니터링 - Loggregation/Monitoring

<img src="https://github.com/limjun92/cloudeNativeStudyProject/blob/master/image/17.png" width="700">
