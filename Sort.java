package interfaceEx.sorting;

public class Sort{
  
	//// 퀵정렬 오름차순
	public static void quick_asc(int[] arr) {
		quick_asc_(arr, 0, arr.length-1);
	}
	private static void quick_asc_(int[] arr, int po1, int po2) {// arr의 po1부터 po2까지의 요소를 정렬
		//// a점의 값이 기준보다 작으면 그대로 두고 a점 전진
		////             기준보다 크면 값을 b점과 맞바꾼 뒤 b점 전진
		//// 기준~a점전까지는 기준 이하, 맨끝~b점전까지는 기준 초과, a점~b점은 아직미정렬.
		//// a점과 b점이 교차하면 
		////     기준을 가운데로
		////     기준 빼고, 나머지 양쪽을 각자 정렬
		int std=arr[po1];
		int a=po1+1;
		int b=po2;
		for( ; ; ) {
			if( arr[a] > std ) {// 기준보다 큼
				swap(arr, a, b);
				b-=1;
			}else {// 기준보다 작거나 같음
				a+=1;
			}
			if( a==b+1 ) break;// a,b 교차
		}
		swap( arr, po1, b );// 기준을 가운데로
		//// 시작점~b점, a점~끝점을 새로 정렬단계로 보낸다.
		if( po1<b-1 ) quick_asc_( arr, po1, b-1 );
		if( a<po2 ) quick_asc_( arr, a, po2 );
	}
  
  //// 버블정렬 오름차순
	public void bubble_asc(int[] arr) {
		for( int i=1; i<arr.length; i++ )
			for( int k=0; k<arr.length-i; k++ )
				if( arr[k]>arr[k+1] ) {
					int tmp = arr[k]; arr[k] = arr[k+1]; arr[k+1]=tmp;
				}
	}
	
	//// 배열의 두 요소 맞바꾸기
	private static void swap( int[] arr, int a, int b ) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
