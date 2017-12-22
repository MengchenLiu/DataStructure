
class SegmentTree{
	int tree[];

	SegmentTree(int arr[], int n){
    int height = (int)Math.ceil(Math.log(n)/Math.log(2))+1;
    int len = (int)Math.pow(2,height)-1;
    tree = new int[len];
    constructSTUtil(arr,0,n-1,0);
	}

	int constructSTUtil(int arr[], int s, int e, int cur){
    if(s==e){
      tree[cur]=arr[s];
      return tree[cur];
    }
    int m = s+(e-s)/2;
    tree[cur] = constructSTUtil(arr,s,m,2*cur+1)+constructSTUtil(arr,m+1,e,2*cur+2);
    return tree[cur];
	}


  void updateValue(int arr[], int n, int i, int new_val){
    if(i<0||i>n-1) return;
    int diff = new_val-arr[i];
    arr[i]=new_val;
    updateValueUtil(0,n-1,i,diff,0);

	}

	void updateValueUtil(int s, int e, int i, int diff, int cur){
    if(i<s||i>e) return;
    tree[cur] += diff;
    if(s!=e){
      int m = s+(e-s)/2;
      updateValueUtil(s,m,i,diff,2*cur+1);
      updateValueUtil(m+1,e,i,diff,2*cur+2);
    }

	}

	int getSum(int n, int qs, int qe){
    if(qs<0||qe>n-1) return -1;
    return getSumUtil(0,n-1,qs,qe,0);

	}
  int getSumUtil(int s, int e, int qs, int qe, int cur){
    if(qs<=s&&qe>=e) return tree[cur];
    if(qs>e||qe<s) return 0;
    int m = s+(e-s)/2;
    return getSumUtil(s,m,qs,qe,2*cur+1)+getSumUtil(m+1,e,qs,qe,2*cur+2);
	}




	public static void main(String args[]){
		int arr[] = {1, 3, 5, 7, 9, 11};
		int n = arr.length;
		SegmentTree tree = new SegmentTree(arr, n);
    //System.out.println(tree.tree[1]);

		System.out.println("Sum of values in given range = " +
						tree.getSum(n, 1, 3));

		tree.updateValue(arr, n, 1, 10);

		System.out.println("Updated sum of values in given range = " +
				tree.getSum(n, 1, 3));
	}
}
