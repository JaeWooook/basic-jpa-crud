# 📝 JPA 게시판 프로젝트

## 📌 프로젝트 개요

이 프로젝트는 **Java + Spring Boot + JPA**를 활용하여 게시판 기능을 구현한 API 기반 백엔드 애플리케이션입니다. JPA의 핵심 개념 및 기능에 대한 실습과 테스트 코드 작성에 중점을 두고 있으며, 복잡한 비즈니스 로직 없이 JPA 동작 원리 이해와 API 설계에 집중하고 있습니다.

---

## 🎯 프로젝트 목적

- **JPA 핵심 기능 학습**
    - 프록시 객체 활용 (지연 로딩, 더티 체킹 등)
    - `save()` 시 JPA가 자동으로 `INSERT` 또는 `UPDATE`를 판단하는 방식 이해
- *도메인 주도 설계(Domain-Driven Design)**를 통한 패키지 구조 설계
- **API 중심 설계** 및 RESTful한 API 구현
- **JUnit 기반 테스트 코드 작성법 학습**
- 예외 처리 기본 구조 구성 (공통 예외 컨트롤러 등)

---

## 🧱 기술 스택

![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.2-success?logo=spring-boot)![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.x-green?logo=thymeleaf)![JPA](https://img.shields.io/badge/JPA-Hibernate-orange?logo=hibernate)![H2](https://img.shields.io/badge/H2--DB-Embedded-blue?logo=datagrip)![JUnit](https://img.shields.io/badge/JUnit-4.13.1-red?logo=testing-library)![Lombok](https://img.shields.io/badge/Lombok-1.18.x-yellow?logo=java)![Gradle](https://img.shields.io/badge/Gradle-7.x-important?logo=gradle)

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA (Hibernate)
- H2 Database (개발/테스트용)
- JUnit 5

---


## 🛠 ERD

- **NOTICEBOARD**: 게시글 테이블
- **COMMENT**: 댓글 테이블

<img width="620" height="412" alt="JPABoardCRUD" src="https://github.com/user-attachments/assets/1c6bebc0-6714-4567-94e1-4ec609406397" />

    
ERD는 ERDCloud를 기반으로 설계되었습니다.

---

## 🚀 주요 기능 (API)

### 📌 게시판

- 게시글 목록 조회 (페이징, 정렬: 최신순/좋아요/싫어요)
- 게시글 검색 (제목, 내용, 제목+내용)
- 게시글 상세 조회
- 게시글 생성 / 수정 / 삭제
- 게시글 좋아요 / 싫어요 추가 및 취소

### 💬 댓글

- 댓글 목록 조회
- 댓글 생성 / 수정 / 삭제
- 댓글 좋아요 / 싫어요 추가

### ⚠️ 예외처리

- 게시글 도메인 관련 예외 처리
- 댓글 도메인 관련 예외 처리
    
    → 공통 예외 핸들러를 통해 일관된 응답 포맷 제공
    

---

## ✅ 테스트 코드

`JUnit5` 기반의 단위 테스트 및 통합 테스트를 구성했습니다.

- 전체 게시판 리스트 조회
- 게시판 상세 조회
- 제목 검색 / 좋아요 정렬 조회
- 게시판 조회 실패 예외 처리
- 댓글 조회 실패 예외 처리

---

## 📌 기타 참고 사항

- **JPA 프록시 객체 활용**: 엔티티에 `protected` 생성자 활용
- **더티 체킹 (Dirty Checking)**: 프록시 객체를 통한 변경 감지로 update 자동 처리
- **MyBatis처럼 직접 SQL 작성 없이** JPA의 영속성 컨텍스트 이해

---

## 🧾 향후 개선 포인트 (선택사항)

- 사용자 인증/인가 도입 (JWT 기반)
- 프론트엔드 연동 (React/Vue 등)
- Swagger 기반 API 문서화
- TestContainers 기반 통합 테스트 강화
