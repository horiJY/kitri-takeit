package pagination;

public class Pagination {
	private int curPage;		
	private int totalPage; 		
	private int totalContent;	
<<<<<<< HEAD
	private int contentCnt = 5;		
=======
	private int contentCnt = 8;		
>>>>>>> refs/remotes/origin/지현
	private int pageCnt = 5;		
	private int curBlock;		
	private int startBlock;	
	private int endBlock;		
	private int startPage;		
	private int endPage;		
	private boolean prevBtn;	
	private boolean nextBtn;	
	
	public Pagination(int curPage, int totalContent, int contentCnt) {
		this.curPage = curPage;
		this.totalContent = totalContent;
		this.contentCnt = contentCnt;
		
		totalPage = (int)Math.ceil(totalContent/(float)contentCnt);
		
		curBlock = (int)Math.ceil(curPage/(double)pageCnt);
		
		endBlock = (int)Math.ceil(totalContent/(double)(pageCnt*contentCnt));
		
		startPage = (curBlock*pageCnt)-(pageCnt-1);
		
		if(endBlock == curBlock) {
			endPage = totalPage;
		}else {
			endPage = startPage + (pageCnt-1);
		}
		
		if(totalPage > 0 && totalPage < (pageCnt+1)) {
			prevBtn = false;
			nextBtn = false;
		}else if(curPage > 0 && curPage < (pageCnt+1)) {
			prevBtn = false;
			nextBtn = true;
		}else if(endBlock == curBlock) {
			prevBtn = true;
			nextBtn = false;
		}else {
			prevBtn = true;
			nextBtn = true;
		}
	}
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalContent() {
		return totalContent;
	}
	public void setTotalContent(int totalContent) {
		this.totalContent = totalContent;
	}
	public int getContentCnt() {
		return contentCnt;
	}
	public void setContentCnt(int contentCnt) {
		this.contentCnt = contentCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getStartBlock() {
		return startBlock;
	}
	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}
	public int getEndBlock() {
		return endBlock;
	}
	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrevBtn() {
		return prevBtn;
	}
	public void setPrevBtn(boolean prevBtn) {
		this.prevBtn = prevBtn;
	}
	public boolean isNextBtn() {
		return nextBtn;
	}
	public void setNextBtn(boolean nextBtn) {
		this.nextBtn = nextBtn;
	}
}
