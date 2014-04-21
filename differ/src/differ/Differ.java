package differ;

import java.io.BufferedReader;
import java.io.FileReader;

public class Differ {

	String progOutputFile;
	String expectedOutputFile;

	String keyword = null;

	public Differ(String[] args) {
		progOutputFile = args[0];
		expectedOutputFile = args[1];
		if (args.length >= 3) {
			keyword = args[2];
		}
	}

	public static void printUsage() {
		System.out.println("Használat:");
		System.out
				.println("java differ.Differ program_kimenet elvárt_kimenet [kulcsszó]");
		System.out.println("A kulcsszó megadása nem kötelező.");
		System.out
				.println("Ha meg van adva, csak azok a sorok lesznek összehasonlítva, amelyek tartalmazzák.");
	}

	public void doDiff() {
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(
					progOutputFile));
			BufferedReader br2 = new BufferedReader(new FileReader(
					expectedOutputFile));
			try {
				String lineOut;
				String lineExp;
				int i = 1;
				while (true) {
					lineOut = br1.readLine();
					lineExp = br2.readLine();
					if (lineOut == null || lineExp == null) {
						break;
					}
					if (keyword == null
							|| (lineOut.contains(keyword) || lineExp
									.contains(keyword))) {
						System.out.print(i + ". sor: ");
						diffLines(lineOut, lineExp);
					}
					++i;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				br1.close();
				br2.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void diffLines(String out, String exp) {
		if (out.equalsIgnoreCase(exp)) {
			System.out.println("Egyezik");
		} else {
			System.out.print("NEM EGYEZIK: \nKimenet:");
			System.out.println("\t" + out);
			System.out.print("Elvárt: ");
			System.out.println("\t" + exp);
		}

	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Túl kevés argumentum!!!!");
			Differ.printUsage();
			System.exit(-1);
		}
		Differ d = new Differ(args);
		d.doDiff();
	}
}
