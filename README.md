<h1 align="center">Welcome to 정션 Pre-task 👋</h1>

### Use Module

* [Spring Boot](https://spring.io/projects/spring-boot) - 백엔드 프레임워크
* [Maven](https://maven.apache.org/) - 의존성 관리
* [H2](https://www.h2database.com/html/main.html) - 인메모리 데이터베이스
* [Bootstrap](https://getbootstrap.com/) UI-Library
* [Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  17


### 사전과제 체크리스트

- [x] https://start.spring.io 에서 프로젝트를 생성
- [x] H2 설정하기
- [x] Entity 클래스 및 Repository 생성
- [x] 환자 등록
- [x] 환자 삭제
- [x] 환자 조회
- [x] 환자 삭제
- [x] 환자 목록 조회 

### H2 Database Query
- Hospital의 경우 이미 병원코드가 있다는 전제하에 미리 생성
- 환자정보는 와 Hospital정보는 외래키로 연결하여 데이터를 입력할 수 있도록 구성
- Hospital 정보는 hospitalName으로 객체를 검색하여 더 추가 검색할 수 있도록 구성하여 데이터를 더 추가하여 사용할 수 있음.

```sql
-- Create Hospitals
INSERT INTO Hospital (hospital_name, medical_institution_number, director_name)
VALUES ('정션병원', 'JB1232', '김지언');
```

```sql
INSERT INTO Patient (patient_name, patient_id, birthday, phone, gender, hospital_id) VALUES
('홍길동1', '910101-1234567', '1991-10-22', '010-1234-5678', 'M', 1),
('홍길동2', '910102-1234567', '1991-10-23', '010-1234-5679', 'F', 1),
('홍길동3', '910103-1234567', '1991-10-24', '010-1234-5680', 'M', 1),
('홍길동4', '910104-1234567', '1991-10-25', '010-1234-5681', 'F', 1),
('홍길동5', '910105-1234567', '1991-10-26', '010-1234-5682', 'M', 1),
('홍길동6', '910106-1234567', '1991-10-27', '010-1234-5683', 'F', 1),
('홍길동7', '910107-1234567', '1991-10-28', '010-1234-5684', 'M', 1),
('홍길동8', '910108-1234567', '1991-10-29', '010-1234-5685', 'F', 1),
('홍길동9', '910109-1234567', '1991-10-30', '010-1234-5686', 'M', 1),
('홍길동10', '910110-1234567', '1991-10-31', '010-1234-5687', 'F', 1),
('홍길동11', '910111-1234567', '1991-11-01', '010-1234-5688', 'M', 1),
('홍길동12', '910112-1234567', '1991-11-02', '010-1234-5689', 'F', 1),
('홍길동13', '910113-1234567', '1991-11-03', '010-1234-5690', 'M', 1),
('홍길동14', '910114-1234567', '1991-11-04', '010-1234-5691', 'F', 1),
('홍길동15', '910115-1234567', '1991-11-05', '010-1234-5692', 'M', 1),
('홍길동16', '910116-1234567', '1991-11-06', '010-1234-5693', 'F', 1),
('홍길동17', '910117-1234567', '1991-11-07', '010-1234-5694', 'M', 1),
('홍길동18', '910118-1234567', '1991-11-08', '010-1234-5695', 'F', 1),
('홍길동19', '910119-1234567', '1991-11-09', '010-1234-5696', 'M', 1),
('홍길동20', '910120-1234567', '1991-11-10', '010-1234-5697', 'F', 1);
```

### API 엔드포인트

API 문서에 접근하려면 브라우저를 통해 [localhost:8000/swagger/](http://localhost:8000/swagger/)에 접속하세요.

1. GET
- `GET /patients/pagenation`: 환자정보를 Pagenation으로 불러오기
- `GET /patients/list`: 환자 정보를 리스트로 전체 불러오기

2. Post
- `POST /patients/create`: 새로운 환자 정보를 생성


### Bootstrap Front-End 

H2 Console을 이용하여 Query를 통해 데이터를 입력 후  [localhost:8080/patients.html](localhost:8080/patients.html)에 접속하세요.

1. PageLayout
- Patient Layout은 환자 정보에 대해 Pagenation을 기본 API로 호출해오고 있습니다.
- Table에서 Detail은 환자 상세정보를 확인할 수 있습니다.
- Delete버튼은 환자정보를 삭제할 수 있습니다.

<p align="center">
  <img src="https://github.com/Ji-Eon/HD_Works/blob/HD_Dev_Works/git_images/page_layout.png?raw=true">
</p>

2. Search
- 환자정보에 대한 검색은 환자이름,환자번호,생년월일로 검색이 가능합니다.

<p align="center">
  <img src="https://github.com/Ji-Eon/HD_Works/blob/HD_Dev_Works/git_images/pname_search_1.png?raw=true">
</p>

<p align="center">
  <img src="https://github.com/Ji-Eon/HD_Works/blob/HD_Dev_Works/git_images/pname_search_2.png?raw=true">
</p>

<p align="center">
  <img src="https://github.com/Ji-Eon/HD_Works/blob/HD_Dev_Works/git_images/p_id_search_1.png?raw=true">
</p>

3. ADD_Pateient
- 환자정보 추가는 환자이름 , 아이디 ( 추후 자동부여로 변경 ) ,생년월일 ,전화번호 , 성별

<p align="center">
  <img src="https://github.com/Ji-Eon/HD_Works/blob/HD_Dev_Works/git_images/create_patient_modal.png?raw=true">
</p>

4. Patient Detail
- 환자 상세 정보를 확인할 수 있습니다.
- 버튼을 클릭하면 환자 정보에 대한 Modal을 불러 옵니다.

<p align="center">
  <img src="https://github.com/Ji-Eon/HD_Works/blob/HD_Dev_Works/git_images/detail_modal_result.png?raw=true">
</p>

5. Delete 
- 환자 정보를 삭제합니다. ( 의료정보의 경우 10년 보관해야 합니다.)
- 환자 정보를 삭제하기 전 한번더 확인 Modal이 나타 납니다.

<p align="center">
  <img src="https://github.com/Ji-Eon/HD_Works/blob/HD_Dev_Works/git_images/delete_modal.png?raw=true">
</p>


### Test Code & Spring-restDOc
1. Test코드 작성 및 실행 : PatientControllerDocumentationTest.java파일을 실행하면 아래와 같이 테스트를 수행하였습니다.
<p align="center">
  <img src="https://github.com/Ji-Eon/HD_Works/blob/en_dev/git_images/controller_test_result.png?raw=true">
</p>

2. adoc파일을 생성하고 아래와 같이 접속하시면 TestCode에 대한 rest-docs를 확인할 수 있습니다.


### 회고 및 향후 Sprint
- PatientID의 경우 규칙성을 만드는것 -> ext ) 정션병원 -> js12...js13.. 각 병원마다 PatientID를 부여하는 법이 달라 규칙성이 없이 저장되게 한부분이 아쉽습니다.
- HospitalName으로 Object값을 기준으로 정보를 입력하고 있어 하나의 병원 또는 EMR자체를 같이 공유하는 병원에 경우에 대한 고민이 부족한 점이 아쉽습니다.
- EMR의 경우 현재 대학병원에서 데이터가 부족한경우 로컬병원에도 요청하는 사례가 빈번하게 이루어져 있어 PatientInformation에 대한 규칙정이 부족한 부분이 아쉽습니다.
- 현재, 맥북이 고장나서... 오랜만에 윈도우환경에서 과제를 하고있어 윈도우에 대한 개발환경 이해도가 낮아 아쉽습니다...ㅜㅜ
- 갑자기 코로나에 걸렸습니다..


## Developer

👤 **Ji-Eon**
=======

* Github: [@Ji-Eon](https://github.com/Ji-Eon)

## Show your support

Give a ⭐️ if this project helped you!