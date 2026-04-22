# BudgetPath (얼마짜리) - PRD & 개발 지시서

## 1. 서비스 개요

이 서비스는 추천이 아닌 **결정 서비스**이다.
사용자가 예산, 지역, 성향을 입력하면 하나의 최적 여행 코스를 계산하여 제공한다.

---

## 2. 핵심 컨셉

- 추천 ❌
- 하나의 최적 결과 ✅
- 사용자는 선택하지 않고 결과를 받는다

---

## 3. 사용자 입력

```json
{
  "budget": 1000000,
  "region": "Tokyo",
  "duration_days": 3,
  "preferences": {
    "food": 0.4,
    "hotel": 0.3,
    "experience": 0.3
  }
}
```

---

## 4. 기술 스택

### Frontend
- 초기: HTML / CSS / JavaScript
- 이후: React 또는 Vue

### Backend
- Java Spring Boot

### Database
- MySQL

---

## 5. 데이터 모델

### places

```sql
places (
  id BIGINT,
  name VARCHAR,
  category ENUM('food', 'hotel', 'experience'),
  region VARCHAR,
  price INT,
  rating FLOAT,
  duration_minutes INT,
  score FLOAT,
  lat DOUBLE,
  lng DOUBLE
)
```

### reviews

```sql
reviews (
  id BIGINT,
  place_id BIGINT,
  content TEXT,
  summarized TEXT,
  sentiment_score FLOAT
)
```

---

## 6. 점수 계산

최종 점수:

```
(평점 * 0.5) + (리뷰 감정 * 0.3) + (인기도 * 0.2)
```

카테고리 가중치 적용:

```
food       → * preference.food
hotel      → * preference.hotel
experience → * preference.experience
```

---

## 7. 핵심 알고리즘

### 문제 정의

- 예산 제한
- 시간 제한
- 카테고리 가중치

→ Knapsack + TSP 혼합 문제

### 단계

1. 후보 필터링 (지역, 가격)
2. 카테고리별 분류
3. 예산 내 조합 생성
4. 점수 최대화
5. 동선 최적화 (Greedy → TSP 확장)

---

## 8. API

### 여행 생성
```
POST /api/trips/generate
```

### 장소 조회
```
GET /api/places
```

---

## 9. AI 활용

### 초기
- GPT/Claude로 장소 데이터 생성

### 확장
- 리뷰 수집 후 요약 및 감정 분석

---

## 10. 프론트

- 입력 페이지
- 결과 페이지

---

## 11. 개발 계획 (2주)

### 1주차
- Spring Boot 세팅
- DB 연결
- 장소 데이터 입력
- 기본 알고리즘

### 2주차
- API 완성
- 프론트 연결
- 테스트

---

## 12. 핵심 차별점

추천 서비스 ❌
결정 서비스 ✅

---

## 13. 보완 필요

- 데이터 출처 확정
- 동선 최적화 강화
- 호텔 1개 고정
- 시간 제한 추가

---

## 14. 전략

완벽함보다 **끝까지 동작하는 MVP** 먼저 만든다.
