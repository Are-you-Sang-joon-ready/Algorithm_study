class Solution {
  public void sortColors(int[] nums) {
    int current = 0;
    int zeroIndex = 0;
    int twoIndex = nums.length - 1;

    while(current <= twoIndex){
      if(nums[current] == 0){
        swap(nums, current++, zeroIndex++);
      }else if(nums[current] == 2){
        swap(nums, current, twoIndex--);
      }else{
        current++;
      }
    }

  }

  private void swap(int[] nums, int current, int moveIndex){
    int temp = nums[current];
    nums[current] = nums[moveIndex];
    nums[moveIndex] = temp;
  }
}