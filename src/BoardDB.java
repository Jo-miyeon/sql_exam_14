import java.util.ArrayList;
import java.util.Scanner;

public class BoardDB {

	public static void main(String[] args) {
		ArticleDao articleDao = new ArticleDao();
		MemberDao memberDao = new MemberDao();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("명령어를 입력해주세요 : ");
			String cmd = sc.nextLine();
			if (cmd.equals("list")) {
				ArrayList<Article> articles = articleDao.getArticles();
				for (int i = 0; i < articles.size(); i++) {
					System.out.println(articles.get(i).getTitle());
					System.out.println(articles.get(i).getBody());
					System.out.println(articles.get(i).getNickname());
					System.out.println(articles.get(i).getRegDate());
				}
			} else if (cmd.equals("sign up")) {
				Member member = new Member();
				System.out.println("==== 회원 가입을 진행합니다 ====");
				System.out.println("아이디를 입력해주세요: ");
				String signupId = sc.nextLine();
				member.setId(signupId);
				System.out.println("비밀번호를 입력해주세요: ");
				String signupPw = sc.nextLine();
				member.setPw(signupPw);
				System.out.println("닉네임을 입력해주세요: ");
				String signupNn = sc.nextLine();
				member.setNn(signupNn);
				memberDao.insertSignup(signupId, signupPw, signupNn);
				System.out.println("==== 회원가입이 완료되었습니다. ====");
			} else if (cmd.equals("sign in")) {
				ArrayList<Member> members = memberDao.getMembers();
				Member member = new Member();
				System.out.println("아이디 :");
				String signinId = sc.nextLine();
				System.out.println("비밀번호 :");
				String signinPw = sc.nextLine();
				for(int i=0; i<members.size();i++) {
					if (member.getId() == signinId && member.getPw() == signinPw) {
						System.out.println(member.getNn() + "님 환영합니다.");
					} else {
						System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
					}	
				}
				
			} else if (cmd.equals("update")) {
				System.out.print("수정할 게시물 번호 : ");
				int aid = Integer.parseInt(sc.nextLine());

				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				articleDao.updateArticle(title, body, aid);
			} else if (cmd.equals("delete")) {
				System.out.print("삭제할 게시물 번호 : ");
				int aid = Integer.parseInt(sc.nextLine());
				articleDao.deleteArticle(aid);
			} else if (cmd.equals("add")) {
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();

				articleDao.insertArticle(title, body);
			} else if (cmd.equals("read")) {
				System.out.print("상세보기할 게시물 번호 : ");
				int aid = Integer.parseInt(sc.nextLine());

				Article article = articleDao.getArticleById(aid);

				if (article == null) {
					System.out.println("없는 게시물입니다.");
				} else {
					while (true) {
						System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
						int dcmd = Integer.parseInt(sc.nextLine());
						if (dcmd == 1) {
							System.out.print("내용을 입력해주세요 :");
							String body = sc.nextLine();
							articleDao.insertReply(article.getId(), body);
						} else {
							break;
						}
					}
				}
			}
		}
	}
}
