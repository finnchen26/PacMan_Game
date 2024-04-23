public class MysteryArray {
    public static void main(String[] args) {
        MysteryArray x = new MysteryArray();
    }

    public int[] nums = new int[10];

    public MysteryArray(){
        for (int y = 0; y < nums.length; y++){
            nums[y] = (int)(Math.random()*100);
        }

        displayNums();

    }

    public void displayNums(){
        for (int i = 0; i < nums.length; i++){
            System.out.println(nums[i]);
        }
    }

}
