# Whiteship Live-Study Participant Checker
> 이 프로그램은 해당 백기선님이 진행하시는 스터디의 출석 체크 프로그램이다.   
> 백깃허브 레포지토리에 각 주차 이슈가 있고, 그 이슈에 코멘트로 링크를 올리면 출석이 인정된다.  
> [스터디 링크](https://github.com/whiteship/live-study)

<br>

## 구현 기능 목록
- 스터디 참석자 목록을 만든다.
- 스터디 참석자의 출석률을 계산하고 보여준다. 

## 스터디 참석자 목록 만들기
- 깃허브에 연동한 뒤 이슈 리스트를 가져온다.
- 이슈 리스트를 <주차, 이슈>로된 Hashmap으로 저장한다. (IssueRepository)
- 모든 이슈를 돌면서 코멘트 작성자의 이름을 업데이트 한다.
    - 참석자 목록에 등록한다. (Hashmap으로 된 목록에 넣어서 중복이 발생하지 않도록)

<br>

## 스터디 참석자 출석률 계산
- 참석자 객체가 참석한 주차 이슈 리스트를 갖게 한다.
- 참석자 객체에서 참석률을 계산하는 메소드를 갖는다.
- 참석자 리스트를 순회하면서 이름과 출석률을 출력한다.

## 필요한 객체
- 깃허브 (API)
- 깃허브 레포지토리 (API)
- 깃허브 이슈 (API)
- 이슈 레포지토리
- 참석자 목록
- 참석자 객체

<br>

#### gradle에서 junit5 실행하기
https://unhosted.tistory.com/41 참고

#### Map을 List로 바꾸는 방법
https://codechacha.com/ko/java-convert-map-to-list/