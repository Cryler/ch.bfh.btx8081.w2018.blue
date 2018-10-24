
/**
 *date: 24.10.2018   -  time: 11:25:08
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package project_test;

public class JavaTestClass {

	public static void main(String[] args) {
		System.out.println("Java test file - Yann / Robin");
		JavaTestClass testing = new JavaTestClass();
		Test abc = testing.new Test(0);
		System.out.println(abc.getI());
		abc.setI(5);
		System.out.println(abc.getI());

	}

	private class Test {
		private int i;

		public Test(int i) {
			this.setI(i);
		}

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i + 5;
		}
	}
}