package com.dbshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.json.simple.JSONObject;

public class Dao {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result = 0;
	String poolUrl = "jdbc:apache:commons:dbcp:pool";

	private void freeConnection(ResultSet r, PreparedStatement p, Connection c) {
		try {
			if (r != null)
				r.close();
			if (p != null)
				p.close();
			if (c != null)
				c.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private void freeConnection(PreparedStatement p, Connection c) {
		try {
			if (p != null)
				p.close();
			if (c != null)
				c.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	// -----------------------------회원가입-----------------------------------------
	public int insertUser(UsersDto dto) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "INSERT INTO users VALUES (NULL,?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getZipCode());
			pstmt.setString(6, dto.getAddress());
			pstmt.setString(7, dto.getAddress2());
			pstmt.setString(8, dto.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
		return result;
	}

	public JSONObject CheckDataJSon(String id) {
		JSONObject json = new JSONObject();
		String rtn = "사용가능한 아이디 입니다.";
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select count(idx) from users where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					rtn = "사용 불가능한 아이디입니다.";
				}
				json.put("rtn", rtn);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return json;
	}

	// ------------------------마이페이지 이동 전 비번체크----------------
	public boolean pwChk(String idx, String id, String pw) {
		boolean result = false;
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select count(idx) from users where idx=? and id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(idx));
			pstmt.setString(2, id);
			pstmt.setString(3, pw);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) { // 값이 있다는거
					result = true;
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return result;
	}

	// ----------------마이페이지-----------------------
	public UsersDto getMyInfo(String id) {
		UsersDto usersDto = new UsersDto();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select * from users where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				usersDto.setIdx(rs.getInt(1));
				usersDto.setId(rs.getString(2));
				usersDto.setPw(rs.getString(3));
				usersDto.setName(rs.getString(4));
				usersDto.setEmail(rs.getString(5));
				usersDto.setZipCode(rs.getString(6));
				usersDto.setAddress(rs.getString(7));
				usersDto.setAddress2(rs.getString(8));
				usersDto.setPhone(rs.getString(9));
				usersDto.setUseyn(rs.getInt(10));
				usersDto.setLv(rs.getInt(11));
				usersDto.setRegedate(rs.getString(12));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return usersDto;
	}

	// -----------------------------로그인-----------------------------------------
	public UsersDto login(String id, String pw) {
		UsersDto usersDto = new UsersDto();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select * from users where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				usersDto.setIdx(rs.getInt(1));
				usersDto.setId(rs.getString(2));
				usersDto.setName(rs.getString(4));
				usersDto.setEmail(rs.getString(5));
				usersDto.setZipCode(rs.getString(6));
				usersDto.setAddress(rs.getString(7));
				usersDto.setAddress2(rs.getString(8));
				usersDto.setPhone(rs.getString(9));
				usersDto.setUseyn(rs.getInt(10));
				usersDto.setLv(rs.getInt(11));
				usersDto.setRegedate(rs.getString(12));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return usersDto;
	}

	// --------------------회원정보 수정----------------------
	public int updateUsers(UsersDto usersDto) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "update users set pw=?, email=?, zipCode=?, address=?, address2=?, phone=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, usersDto.getPw());
			pstmt.setString(2, usersDto.getEmail());
			pstmt.setString(3, usersDto.getZipCode());
			pstmt.setString(4, usersDto.getAddress());
			pstmt.setString(5, usersDto.getAddress2());
			pstmt.setString(6, usersDto.getPhone());
			pstmt.setString(7, usersDto.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
		return result;
	}

	// ------------------회원 탈퇴---------------------------
	public int deleteUsers(String id) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "update users set useyn=2 where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
		return result;
	}

	// -----------------------------주문-----------------------------------------
	// 주문 입력
	public int insertOrder(OrderDto dto) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "INSERT INTO orders VALUES (NULL,?,?,?,DEFAULT,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, dto.getProdnum());
			pstmt.setInt(3, dto.getQuantity());
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
		return result;
	}

	public OrderDto readOrder(int orderNum) {
		OrderDto odto = new OrderDto();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM orders WHERE orderNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				odto.setOrderNum(rs.getInt(1));
				odto.setId(rs.getString(2));
				odto.setProdnum(rs.getInt(3));
				odto.setQuantity(rs.getInt(4));
				odto.setResult(rs.getString(5));
				odto.setOrderDate(rs.getString(6));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return odto;
	}

	// 주문내역 확인
	public Vector<OrderDto> selectOrder(String id) {
		Vector<OrderDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM orders where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderDto dto = new OrderDto();
				dto.setOrderNum(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setProdnum(rs.getInt(3));
				dto.setQuantity(rs.getInt(4));
				dto.setResult(rs.getString(5));
				dto.setOrderDate(rs.getString(6));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// -----------------------------배송관리-----------------------------------------
	// 주문내역 모두 들고오기
	public Vector<OrderDto> selectOrder() {
		Vector<OrderDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM orders";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderDto dto = new OrderDto();
				dto.setOrderNum(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setProdnum(rs.getInt(3));
				dto.setQuantity(rs.getInt(4));
				dto.setResult(rs.getString(5));
				dto.setOrderDate(rs.getString(6));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}
	// 배송 결과 확인
	public Vector<OrderDto> deliveryCheck(int res) {
		Vector<OrderDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM orders where result=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, res);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderDto dto = new OrderDto();
				dto.setOrderNum(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setProdnum(rs.getInt(3));
				dto.setQuantity(rs.getInt(4));
				dto.setResult(rs.getString(5));
				dto.setOrderDate(rs.getString(6));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}
	// 배송
	public void delivery(int orderNum) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "UPDATE orders SET result=2 WHERE orderNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	// 배송
	public void deliveryCancel(int orderNum) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "UPDATE orders SET result=1 WHERE orderNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNum);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	// -----------------------------장바구니-----------------------------------------
	public Vector<CartDto> selectCart(String id) { // 기존
		Vector<CartDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM cart where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartDto dto = new CartDto();
				dto.setCartNum(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setProdnum(rs.getInt(3));
				dto.setQuantity(rs.getInt(4));
				dto.setCartDate(rs.getString(5));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// 장바구니 입력
	public void insertCart(String id, int prodnum, int quantity) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "INSERT INTO cart VALUES(NULL,?,?,?,NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, prodnum);
			pstmt.setInt(3, quantity);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	// 장바구니 삭제
	public void deleteCart(int cartNum) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "DELETE FROM cart WHERE cartNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNum);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	// 장바구니 비우기
	public void ClearCart(String id) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "DELETE FROM cart WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	public void selDeleteCart(int cartNum) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "DELETE FROM cart WHERE cartNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cartNum);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	// 카트주문
	public ProductDto cartProductOrder(int productNum) {
		ProductDto pDto = new ProductDto();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM product WHERE productNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pDto.setProductNum(rs.getInt(1));
				pDto.setKind(rs.getString(2));
				pDto.setPrice(rs.getInt(3));
				pDto.setName(rs.getString(4));
				pDto.setContents(rs.getString(5));
				pDto.setImage(rs.getString(6));
				pDto.setHit(rs.getInt(7));
				pDto.setRegdate(rs.getString(8));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return pDto;
	}
	//카트 주문
	public int cartInsertOrder(String id, int prodnum, int quantity) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "INSERT INTO orders VALUES (NULL,?,?,?,DEFAULT,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, prodnum);
			pstmt.setInt(3, quantity);
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
		return result;
	}
	//카트 주문 후 삭제
	
	// -----------------------------게시물-----------------------------------------
	// 전체 조회
	public Vector<ProductDto> selectProduct() {
		Vector<ProductDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt(1));
				dto.setKind(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setName(rs.getString(4));
				dto.setContents(rs.getString(5));
				dto.setImage(rs.getString(6));
				dto.setHit(rs.getInt(7));
				dto.setRegdate(rs.getString(8));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	public Vector<ProductDto> selectProductKind(int kind) {
		Vector<ProductDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM product WHERE kind=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt(1));
				dto.setKind(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setName(rs.getString(4));
				dto.setContents(rs.getString(5));
				dto.setImage(rs.getString(6));
				dto.setHit(rs.getInt(7));
				dto.setRegdate(rs.getString(8));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// 회원이 게시글 조회
	public ProductDto ReadProduct2(int productNum) {
		ProductDto pDto = new ProductDto();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM product WHERE productNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pDto.setProductNum(rs.getInt(1));
				pDto.setKind(rs.getString(2));
				pDto.setPrice(rs.getInt(3));
				pDto.setName(rs.getString(4));
				pDto.setContents(rs.getString(5));
				pDto.setImage(rs.getString(6));
				pDto.setHit(rs.getInt(7));
				pDto.setRegdate(rs.getString(8));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return pDto;
	}

	// 게시글 조회, 게시판 수정 조회
	public Vector<ProductDto> ReadProduct(int productNum) {
		Vector<ProductDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM product WHERE productNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt(1));
				dto.setKind(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setName(rs.getString(4));
				dto.setContents(rs.getString(5));
				dto.setImage(rs.getString(6));
				dto.setHit(rs.getInt(7));
				dto.setRegdate(rs.getString(8));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// 게시판 업로드
	public void boardInsert(String kind, int price, String name, String contents, String image) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "INSERT INTO product VALUES (NULL, ?, ?, ?, ?, ?, DEFAULT, NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setInt(2, price);
			pstmt.setString(3, name);
			pstmt.setString(4, contents);
			pstmt.setString(5, image);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	// 게시판 수정
	public void boardInsert(int productNum, String kind, int price, String name, String contents, String image) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "UPDATE product SET kind=?, price=?, NAME=?, contents=?, image=?, regdate=NOW() WHERE productNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setInt(2, price);
			pstmt.setString(3, name);
			pstmt.setString(4, contents);
			pstmt.setString(5, image);
			pstmt.setInt(6, productNum);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	// 게시물 삭제
	public void DeleteProduct(int productNum) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "DELETE FROM product WHERE productNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNum);
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	// 게시물 검색
	public Vector<ProductDto> searchProduct(String kind, String insertText) {
		Vector<ProductDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM product WHERE kind=? and name LIKE ?"; //search는 text로 입력한 값임
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			pstmt.setString(2, "%" + insertText + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt(1));
				dto.setKind(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setName(rs.getString(4));
				dto.setContents(rs.getString(5));
				dto.setImage(rs.getString(6));
				dto.setHit(rs.getInt(7));
				dto.setRegdate(rs.getString(8));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	public Vector<ProductDto> searchProduct(String insertText) {
		Vector<ProductDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM product WHERE NAME LIKE ?"; //여기에 %쓰면 안됨
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + insertText + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDto dto = new ProductDto();
				dto.setProductNum(rs.getInt(1));
				dto.setKind(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setName(rs.getString(4));
				dto.setContents(rs.getString(5));
				dto.setImage(rs.getString(6));
				dto.setHit(rs.getInt(7));
				dto.setRegdate(rs.getString(8));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// ---------------- 전체 board 게시글 갯수 -----------------
	public int selectBoardCnt() {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select count(productNum) from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return result;
	}

	// ------------------- 페이징 처리한 board -------------------
	public Vector<ProductDto> selectBoard(int nowPage, int cnt) {
		Vector<ProductDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select * from product limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * cnt);
			pstmt.setInt(2, cnt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDto productDto = new ProductDto();
				productDto.setProductNum(rs.getInt(1));
				productDto.setKind(rs.getString(2));
				productDto.setPrice(rs.getInt(3));
				productDto.setName(rs.getString(4));
				productDto.setContents(rs.getString(5));
				productDto.setImage(rs.getString(6));
				productDto.setHit(rs.getInt(7));
				productDto.setRegdate(rs.getString(8));
				v.add(productDto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// -----------------------------QnA게시판-----------------------------------------
	public int insertQna(QnaDto dto) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "INSERT INTO qna VALUES (NULL,?,?,?,NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
		return result;
	}

	public Vector<QnaDto> selectData(int nowPage, int cnt) {
		Vector<QnaDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM qna order by qnaNum desc limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * cnt);
			pstmt.setInt(2, cnt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaDto dto = new QnaDto();
				dto.setQnaNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setQdate(rs.getString(5));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	public Vector<QnaDto> selectQnaData() {
		Vector<QnaDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM qna order by qnaNum desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaDto dto = new QnaDto();
				dto.setQnaNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setQdate(rs.getString(5));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// qna 게시물 찾기
	public Vector<QnaDto> searchQna(String item, String insertText) {
		Vector<QnaDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM qna WHERE " + item + " LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + insertText + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				QnaDto dto = new QnaDto();
				dto.setQnaNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setQdate(rs.getString(5));
				v.add(dto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// qna 게시물 찾기 갯수

	public int searchQnaCnt(String insertText) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select count(qnaNum) from qna WHERE title LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + insertText + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return result;
	}

	public Vector<QnaDto> searchSelectQna(String insertText, int nowPage, int cnt) {
		Vector<QnaDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "(select * from qna WHERE title LIKE ? limit ?, ?)order by qnaNum DESC ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + insertText + "%");
			pstmt.setInt(2, (nowPage - 1) * cnt);
			pstmt.setInt(3, cnt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaDto qnaDto = new QnaDto();
				qnaDto.setQnaNum(rs.getInt(1));
				qnaDto.setTitle(rs.getString(2));
				qnaDto.setContent(rs.getString(3));
				qnaDto.setId(rs.getString(4));
				qnaDto.setQdate(rs.getString(5));
				v.add(qnaDto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// ---------------- 전체 qna 게시글 갯수 -----------------
	public int selectQnaCnt() {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select count(qnaNum) from qna order by qnaNum desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return result;
	}

	public QnaDto ReadQnaData(int qnaNum) {
		QnaDto dto = new QnaDto();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM qna WHERE qnaNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setQnaNum(rs.getInt(1)); // 이거 테이블 순서대로 해야함..★★★★★
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setQdate(rs.getString(5));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return dto;
	}

	// 삭제
	public void deleteQnaData(int qnaNum) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "DELETE from qna where qnaNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	public void updateQnaData(QnaDto qnaDto) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "update qna set title=?,content=? where qnaNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaDto.getTitle());
			pstmt.setString(2, qnaDto.getContent());
			pstmt.setInt(3, qnaDto.getQnaNum());
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	// ------------------- 페이징 처리한 qna -------------------
	public Vector<QnaDto> selectQna(int nowPage, int cnt) {
		Vector<QnaDto> v = new Vector<>();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select * from qna order by qnaNum desc limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (nowPage - 1) * cnt);
			pstmt.setInt(2, cnt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaDto qnaDto = new QnaDto();
				qnaDto.setQnaNum(rs.getInt(1));
				qnaDto.setTitle(rs.getString(2));
				qnaDto.setContent(rs.getString(3));
				qnaDto.setId(rs.getString(4));
				qnaDto.setQdate(rs.getString(5));
				v.add(qnaDto);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return v;
	}

	// qnaRead 이전글 가기
	public QnaDto ReadPrevQnaData(int qnaNum) {
		QnaDto dto = new QnaDto();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM qna WHERE qnaNum=(select max(qnaNum) from qna where qnaNum<?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setQnaNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setQdate(rs.getString(5));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return dto;
	}

	// qnaRead 다음글 가기
	public QnaDto ReadNextQnaData(int qnaNum) {
		QnaDto dto = new QnaDto();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM qna WHERE qnaNum=(select min(qnaNum) from qna where qnaNum>?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setQnaNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setId(rs.getString(4));
				dto.setQdate(rs.getString(5));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return dto;
	}
	// -----------------------------QnA답변-----------------------------------------

	public int insertAnswer(QnaAnswerDto dto) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "INSERT INTO qnaAnswer VALUES (?,?,NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getAnswerNum());
			pstmt.setString(2, dto.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
		return result;
	}

	public QnaAnswerDto readAnswer(int answerNum) {
		QnaAnswerDto dto = new QnaAnswerDto();
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "SELECT * FROM qnaAnswer WHERE answerNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, answerNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setAnswerNum(rs.getInt(1));
				dto.setContent(rs.getString(2));
				dto.setRegdate(rs.getString(3));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return dto;
	}

	public void deleteAnswer(int qnaNum) { 
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "DELETE from qnaAnswer where answerNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

	public boolean checkExistAns(int qnaNum) {
		boolean result = false;
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "select count(answerNum) from qnaAnswer where answerNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnaNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0) { // 값이 있다는거
					result = true;
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(rs, pstmt, conn);
		}
		return result;
	}

	// --------------------- 상품 게시글 조회수 ----------------
	public void ReadHit(ProductDto productDto) {
		try {
			conn = DriverManager.getConnection(poolUrl);
			String sql = "update product set hits=hits+1 where productNum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productDto.getProductNum());
			pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			freeConnection(pstmt, conn);
		}
	}

}
