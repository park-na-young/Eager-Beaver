# Eager-Beaver
<div style="text-align: center;">
  <img src="https://github.com/park-na-young/Eager-Beaver/blob/dev/backend-app/src/main/resources/image/readme/eager-beaver%20icon.png" alt="eager-beaver 아이콘" width="30%" style="display: block; margin: 0 auto;"/>
</div>



## 1. 서비스 소개 
- "Eager-beaver" 는 "매우 열정적이고 부지런한 사람" 이라는 뜻으로 영어권에서 자주 쓰이는 관용적인 표현입니다. 

- **크롬 확장 프로그램**으로, 사용자가 웹을 이용하는 동안 화면에 배너 형태로 표시되어 일상의 생산성을 높여줍니다.
  - 크롬 확장프로그램 ([크롬 익스텐션 (chrome extension)](https://chromewebstore.google.com/category/extensions)) 이란?
      
	구글 크롬 웹 브라우저의 기능을 확장하거나 추가할 수 있도록 도와주는 프로그램입니다. 사용자가 원하는 기능을 브라우저에 손쉽게 추가할 수 있으며, 크롬 웹 스토어(Chrome Web Store)를 통해 설치할 수 있습니다. HTML, CSS, JavaScript 등 웹기술을 사용해 개발이 가능합니다.

## 2. 주요기능 
- **To-Do 리스트**: 간단하고 직관적으로 할 일을 추가하고 관리할 수 있습니다.
- **타이머**: 작업 집중도를 높이기 위한 타이머 기능 제공합니다.
- **D-Day 설정**: 중요한 날을 잊지 않도록 D-Day를 설정하여 표시합니다.
- **배너 커스터마이징**: 사용자가 배너 사진, 글자색 등을 직접 꾸며 자신만의 스타일로 설정 가능합니다. 

## 3. 기술스택

#### Frontend
![Next.js](https://img.shields.io/badge/Next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white)

#### Backend
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)

#### 배포 환경
![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)
![AWS RDS](https://img.shields.io/badge/AWS%20RDS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)


#### 협업 툴
![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)

#### 디자인
![Figma](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)

## 4. 애플리케이션 UI 
--- 추후추가---
## 5. 기술적 이슈 
- AWS EC2 서버에 Redis 설치

로그인 시 토큰을 저장하기 위해 **Redis**를 활용하였습니다. Redis는 빠른 데이터 조회와 캐싱을 위해 선택되었으며, 배포 과정에서 Redis는 EC2 서버에 설치하고, 외부에서 접근할 수 있도록 설정했습니다. 이를 통해 사용자 세션을 효율적으로 관리할 수 있었고, 빠른 데이터 처리가 가능해졌습니다. 
</br>
** 관련 개발을 바탕으로 정리한 내용은 [여기에서 확인하세요](https://some4956.tistory.com/entry/Redis-%ED%8A%B8%EB%9F%AC%EB%B8%94-%EC%8A%88%ED%8C%85-%EB%A0%88%EB%94%94%EC%8A%A4-%EC%99%B8%EB%B6%80-%EC%97%B0%EA%B2%B0-%EC%84%A4%EC%A0%95-%ED%9B%84-%EC%97%90%EB%9F%AC-%EB%B0%9C%EC%83%9D-Failed-to-start-redis-serverservice-Advanced-key-value-store)"**


..... 

## 6. 팀원 소개 
- **박나영** : 백엔드 [] () 
- **임현홍** : 프론트엔드 [] () 

      






