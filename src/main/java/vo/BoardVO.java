package vo;

import lombok.Data;

//** Lombok
//setter, getter, toString, 생성자 등을 자동으로 만들어줌
//코드를 줄이고 가독성을 높여 편리하지만, 장.단점이 있어 충분히 고려 후 사용해야 한다.  
//=> 모든 필드의  public setter 와  getter 를 사용하는 일반적인 경우 유용하며, 
//=> 보안을 위해 setter 와  getter 의 접근 범위를 지정해야 하는 경우 등
//=> 대규모의 프로젝트에서 다양한 setter 와  getter code를 작성하는 경우에는 충분히 고려해야함. 

//=> @Data 즉, 다음 어노테이션을 모두 한번에 처리 한다.
//=> @Getter(모든 필드) : getter 생성하도록 지원
//=> @Setter(모든 필드-final로 선언되지 않은) : setter를 생성하도록 지원
//=> @ToString :  모든 필드를 출력하는 toString() 메소드 생성 

@Data
//=> 정의된 모든 필드에 대한 
//Getter, Setter, ToString 과 같은 모든 요소를 한번에 만들어주는 어노테이션.
public class BoardVO {
	private int seq;
	private String title;
	private String id;
	private String content;
	private String regdate;
	private int cnt;
	private int root;
	private int step;
	private int indent;
	

//	Lombok 사용 전(set, get ,tostring) 
	
//	public int getRoot() {
//		return root;
//	}
//	public void setRoot(int root) {
//		this.root = root;
//	}
//	public int getStep() {
//		return step;
//	}
//	public void setStep(int step) {
//		this.step = step;
//	}
//	public int getIndent() {
//		return indent;
//	}
//	public void setIndent(int indent) {
//		this.indent = indent;
//	}
//	public int getSeq() {
//		return seq;
//	}
//	public void setSeq(int seq) {
//		this.seq = seq;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
//	public String getRegdate() {
//		return regdate;
//	}
//	public void setRegdate(String regdate) {
//		this.regdate = regdate;
//	}
//	public int getCnt() {
//		return cnt;
//	}
//	public void setCnt(int cnt) {
//		this.cnt = cnt;
//	}
//	@Override
//	public String toString() {
//		return "BoardVO [seq=" + seq + ", title=" + title + ", id=" + id + ", content=" + content + ", regdate="
//				+ regdate + ", cnt=" + cnt + ", root=" + root + ", step=" + step + ", indent=" + indent + "]";
//	}
	
} //class
