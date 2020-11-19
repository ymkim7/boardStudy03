package kr.co.util;

public class BoardPager {
	
	//페이징당 게시물 수
	public static final int PAGE_SCALE = 10;
	
	//화면당 페이지 수
	public static final int BLOCK_SCALE = 10;
	
	//현재 페이지수
	private int curPage;
	//이전 페이지
	private int prevPage;
	//다음 페이지
	private int nextPage;
	//전체 페이지 갯수
	private int totPage;
	//전체 페이지 블록 갯수
	private int totBlock;
	//현재 페이지 블록
	private int curBlock;
	//이전 페이지 블록
	private int prevBlock;
	//다음 페이지 블록
	private int nextBlock;
	
	//WHERE rn BETWEEN #{start} AND #{end}
	//#{start}
	private int pageBegin;
	//#{end}
	private int pageEnd;
	
	//이전 blockBegin -> 41 42 43 44 45 46 47 48 49 50 다음
	//현재 페이지 블록의 시작번호
	private int blockBegin;
	//이전 41 42 43 44 45 46 47 48 49 50 <- blockEnd 다음
	private int blockEnd;
	
	//생성자
	//BoardPager(레코드 갯수, 현재 페이지 번호)
	public BoardPager(int count, int curPage) {
		
		//현재 페이지 블록 번호
		curBlock = 1;
		//현재 페이지 설정
		this.curPage = curPage;
		//전체 페이지 갯수 계산
		setTotPage(count);
		setPageRange();
		//전체 페이지 블록 갯수 계산
		setTotBlock();
		//페이지 블록의 시작, 끝 번호 계산
		setBlockRange();
	}
	
	public void setBlockRange() {
		
		//현재 페이지가 몇번째 페이지 블록에 속하는지 계산
		curBlock = (int) Math.ceil((curPage - 1) / BLOCK_SCALE) + 1;
		//현재 페이지 블록의 시작, 끝 번호 계산
		blockBegin = (curBlock-1) * BLOCK_SCALE + 1;
		//페이지 블록위 끝번호
		blockEnd = blockBegin + BLOCK_SCALE - 1;
		//마지막 블록이 범위를 초과하지 않도록 계산
		if(blockEnd > totPage) blockEnd = totPage;
		//이전을 눌렀을 때 이동할 페이지 번호
		prevPage = (curPage == 1)? 1:(curBlock - 1) * BLOCK_SCALE;
		//다음을 눌렀을 때 이동할 페이지 번호
		nextPage = curBlock > totBlock ? (curBlock * BLOCK_SCALE) : (curBlock * BLOCK_SCALE) + 1;
		//마지막 페이지가 범위를 초과히자 않도록 처리
		if(nextPage >= totPage) nextPage = totPage;
		
	}
	
	public void setPageRange() {
		
		//WHERE rn BETWEEN #{start} AND {end}
		//시작번호 = (현제페이지 - 1) * 페이지당 게시물수 + 1
		pageBegin = (curPage - 1) * PAGE_SCALE + 1;
		//끝번호 = 시작번호 + 페이지당 게시물수 -1
		pageEnd = pageBegin + PAGE_SCALE - 1;
		
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotPage() {
		return totPage;
	}

	public void setTotPage(int count) {
		//Math.ceil(실수) 올림 처리
		totPage = (int) Math.ceil(count * 1.0 / PAGE_SCALE);
	}

	public int getTotBlock() {
		return totBlock;
	}

	//페이지 블록의 갯수 계산 (총 100페이지라면 10개의 블록)
	public void setTotBlock() {
		//전체 페이지 갯수 / 10
		//91 / 10 => 9.1 => 10개
		totBlock = (int) Math.ceil(totPage / BLOCK_SCALE);
	}

	public int getCurBlock() {
		return curBlock;
	}

	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	public static int getPageScale() {
		return PAGE_SCALE;
	}

	public static int getBlockScale() {
		return BLOCK_SCALE;
	}

	public BoardPager(int curPage, int prevPage, int nextPage, int totPage, int totBlock, int curBlock, int prevBlock,
			int nextBlock, int pageBegin, int pageEnd, int blockBegin, int blockEnd) {
		super();
		this.curPage = curPage;
		this.prevPage = prevPage;
		this.nextPage = nextPage;
		this.totPage = totPage;
		this.totBlock = totBlock;
		this.curBlock = curBlock;
		this.prevBlock = prevBlock;
		this.nextBlock = nextBlock;
		this.pageBegin = pageBegin;
		this.pageEnd = pageEnd;
		this.blockBegin = blockBegin;
		this.blockEnd = blockEnd;
	}

	@Override
	public String toString() {
		return "BoardPager [curPage=" + curPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + ", totPage="
				+ totPage + ", totBlock=" + totBlock + ", curBlock=" + curBlock + ", prevBlock=" + prevBlock
				+ ", nextBlock=" + nextBlock + ", pageBegin=" + pageBegin + ", pageEnd=" + pageEnd + ", blockBegin="
				+ blockBegin + ", blockEnd=" + blockEnd + ", toString()=" + super.toString() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blockBegin;
		result = prime * result + blockEnd;
		result = prime * result + curBlock;
		result = prime * result + curPage;
		result = prime * result + nextBlock;
		result = prime * result + nextPage;
		result = prime * result + pageBegin;
		result = prime * result + pageEnd;
		result = prime * result + prevBlock;
		result = prime * result + prevPage;
		result = prime * result + totBlock;
		result = prime * result + totPage;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardPager other = (BoardPager) obj;
		if (blockBegin != other.blockBegin)
			return false;
		if (blockEnd != other.blockEnd)
			return false;
		if (curBlock != other.curBlock)
			return false;
		if (curPage != other.curPage)
			return false;
		if (nextBlock != other.nextBlock)
			return false;
		if (nextPage != other.nextPage)
			return false;
		if (pageBegin != other.pageBegin)
			return false;
		if (pageEnd != other.pageEnd)
			return false;
		if (prevBlock != other.prevBlock)
			return false;
		if (prevPage != other.prevPage)
			return false;
		if (totBlock != other.totBlock)
			return false;
		if (totPage != other.totPage)
			return false;
		return true;
	}

}
