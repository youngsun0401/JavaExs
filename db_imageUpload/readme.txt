cos 라이브러리로써 이미지 업로드

http://www.servlets.com/cos/
에서 아래쪽에 cos 라이브러리 다운로드
lib > jar 파일을 프로젝트의 lib에 넣기

image.jsp 페이지에서 이미지를 선택하고 imageProcess.jsp에 제출한다.

imageProcess.jsp에서는 선택된 이미지를 주어진 경로에 넣고 그걸 또 upload 메서드로써 디비에 넣는다. 두 번 일이지만 일단 다른 수를 모름.

DAO 클래스에는 upload 메서드로; 받은 파일을 디비에 넣는다.
