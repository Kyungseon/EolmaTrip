# [Docker 설정] 

개발 중 (MySQL만 Docker)

cd /Users/kyungseon/dev/eolmatrip

## MySQL 컨테이너만 실행
docker compose up -d mysql

# Spring Boot는 로컬에서
cd backend
mvn spring-boot:run

전체 Docker로 실행 (배포용)

cd /Users/kyungseon/dev/eolmatrip

docker compose --profile full up -d

자주 쓰는 명령어

# 컨테이너 상태 확인
docker compose ps

# MySQL 로그 보기
docker compose logs -f mysql

# MySQL 접속 (확인용)
docker exec -it eolmatrip-mysql mysql -uroot -peolmatrip1234 eolmatrip

# 전체 종료
docker compose down

# 데이터까지 초기화 (DB 리셋)
docker compose down -v

지금 바로 시작하려면 docker compose up -d mysql 실행 후 30초 기다렸다가 Spring Boot 띄우시면 됩니다. Maven 설치 안 돼
있으면 말씀해주세요.
