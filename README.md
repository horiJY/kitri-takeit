
# 페이지 구조
![image](https://user-images.githubusercontent.com/45135200/140075765-2684bf80-aa78-4d05-973e-e1be724ef601.png)


# 페이지별 특징

## menu : 바로수강, 오픈예정, 고객센터

- 바로수강 참고
    - 추천순 정렬
    - 기간, 가격범위, 카테고리(5개),지역 필터링
    - 수업방식 (일회성,다회성,대면,비대면)(ex.스카이스캐너)
    - 미리보기 gif
- 오픈예정 참고
    - 응원하기
    - 미리보기 gif
    - 응원 마감 임박
    - 응원 %
- 고객센터
    - 문의하기
    - 자주묻는 질문
- 마이페이지
    - 수강강좌
    - 수강내역
    - 내문의
    - 내정보
    - 좋아요(관심있어요)설정한강좌

 

[페이지 개발현황(위쪽부터 우선순위높음)의 사본](https://www.notion.so/4ccde10fff3d40fb97a5c5d87a6038ed)

## 기능 구현 리스트

- 회원 정보
    - [x]  - 로그인 > 카카오,네이버,구글 로그인 연동
    - [x]  - 내정보
    - [x]  수강중강좌
    - [x]  수강내역
    - [x]  내문의
- 강의 상세페이지
    - [ ]  위치정보로 지도api사용하여 지도위에 위치표시
    - [ ]  class 테이블 recommand에 5점만점기준으로 별표표시
- 공통
    - [x]  고객센터 문의하기
        - qna 테이블에 create
- [x]  강의 오픈예정
    - 좋아요(관심있어요) 갯수 표시
- [x]  강의 개설
    - class 테이블에 create
    - schedule 테이블에 create
- [ ]  폐강
    - class 테이블에서 delete
    - assignment에서 해당 classid 로 등록한 userid 수강생에게 price 만큼 userpoint 환불
- 수강생
    - [x]  강의 수강
        - price 만큼 userpoint 부족 시 거절
        - 해당 userid 의 assignment 중 중복 클래스가 있다면 거절 (종료일이 해당 강의의 시작일보다 늦는 강의가 있는지 체크)
        - assignment 에 create ()
        - price 만큼 userpoint 차감
    - [ ]  수강 취소
        - 수업 시작 24시간 전이 지난 경우 50% 환불
        - 수업 시작부터 전체 수업의 50% 이하 진행된 경우 30% 환불
        - 수업 시작부터 전체 수업의 50% 이상 진행된 경우 취소 버튼 감추기
        - price 만큼 userpoint 환불
    - [x]  강의 문의
        - class_qna 테이블에 create
    - [ ]  강의 리뷰
    - review 테이블에 create
    - 이미 리뷰를 남긴 사람은 거절
    
![image](https://user-images.githubusercontent.com/45135200/140075862-d27fb332-0bda-4550-b863-2af3df84618e.png)
