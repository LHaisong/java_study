package algorithms.backtrack;


public class AthleteMatch {
	public int n;
	public int[][]p;
	public int[][]q;
	public int[]x;
	public int bestw=0;
	int cw;
	public AthleteMatch(int n_,int[][]p_,int[][]q_){
		n=n_;
		p=p_;
		q=q_;
		x=new int[n+1];
		for(int i=0;i<n+1;i++){
			x[i]=i;
		}
		}
		public void backtrack(int i){
		if(i>n){
			outputX();
		}
		else {
			for(int j=i;j<=n;j++){
				swap(x[i],x[j]);
				backtrack(i+1);
				swap(x[i],x[j]);
			}
		}
	}

	private int outputX() {
		cw=0;
		for(int i=1;i<=n;i++){
			cw+=p[i][x[i]]*q[x[i]][i];
			if(cw>bestw);
			bestw=cw;
		}
		return bestw;
	}
	private void swap(int x, int x1) {
		int temp=x1;
		x1=x;
		x=temp;
	}
	public static void main(String[]args){
		int[][]p= {{10, 2, 3},
				    {2, 3, 4},
				    {3,4,5}
		           };
		int[][]q={
				{2,2,2},
				{3,5,3},
				{4,5,1}
		};
		int n=p.length;
		AthleteMatch am=new AthleteMatch(n,p,q);
		am.backtrack(1);
		System.out.println(am.outputX());
	}
}
