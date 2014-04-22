package differ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class Differ {

	String progOutputFile;
	String expectedOutputFile;

	HashSet<String> keywords = null;

	public Differ(String[] args) {
		progOutputFile = args[0];
		expectedOutputFile = args[1];
		if (args.length >= 3) {
			keywords = new HashSet<String>();
			for (int i = 2; i < args.length; ++i) {
				keywords.add(args[i]);
			}
		}
	}

	public static void printUsage() {
		System.out.println("Használat:");
		System.out
				.println("java differ.Differ program_kimenet elvárt_kimenet [kulcsszavak]");
		System.out.println("Kulcsszó megadása nem kötelező.");
		System.out
				.println("Ha van megadva, csak azok a sorok lesznek összehasonlítva, amelyek tartalmazzák valamelyik kulcsszót.");
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
					if (keywords == null) {
						System.out.print(i + ". sor: ");
						diffLines(lineOut, lineExp);
					} else {
						boolean c = false;
						for (String k : keywords) {
							if (lineOut.contains(k) || lineExp.contains(k)) {
								c = true;
								break;
							}
						}
						if (c) {
							System.out.print(i + ". sor: ");
							diffLines(lineOut, lineExp);
						}
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
		String nospaceout = out.trim();
		String nospaceexp = exp.trim();
		if (nospaceout.equalsIgnoreCase(nospaceexp)) {
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
