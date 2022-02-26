import java.util.Scanner;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Random;

public class Main {

	// Tables and Other Variables //
	private static Vector<Karyawan> listKaryawan = new Vector<Karyawan>();
	private static Vector<Karyawan> ascendingListKaryawan = new Vector<Karyawan>();
	private static Integer amountOfKaryawans = 0;
	private static Integer amountOfManagers = 0;
	private static Integer amountOfSupervisors = 0;
	private static Integer amountOfAdmins = 0;

	// Scanner //
	private static Scanner scan = new Scanner(System.in);

	// Randomizer //
	private static Random random = new Random();

	// Main Function //
	public static void main(String[] args) {
		mainMenu();
	}

	// Main Menu //
	private static void mainMenu(){

		// Clear screen
		clearScreen();

		// // Display list karyawan
		// System.out.println("No	Kode Karyawan	Nama Karyawan	Jenis Kelamin	Jabatan		Gaji Karyawan");
		// for(Integer karyawanIndex = 0; karyawanIndex < listKaryawan.size(); karyawanIndex++){
		// 	System.out.printf("%d 	%s		%s		%s	%s		%.0f", karyawanIndex + 1, listKaryawan.get(karyawanIndex).getIdKaryawan(), listKaryawan.get(karyawanIndex).getNama(), listKaryawan.get(karyawanIndex).getJenisKelamin(), listKaryawan.get(karyawanIndex).getJabatan(), listKaryawan.get(karyawanIndex).getGaji());
		// 	System.out.println();
		// }
		// System.out.println();
		// System.out.println("Number of Employees: " + amountOfKaryawans);
		// System.out.println("Number of Managers: " + amountOfManagers);
		// System.out.println("Number of Supervisors: " + amountOfSupervisors);
		// System.out.println("Number of Admins: " + amountOfAdmins);
		// System.out.println();

		// Print main menu
		System.out.println("1. Insert data karyawan");
		System.out.println("2. View data karyawan");
		System.out.println("3. Update data karyawan");
		System.out.println("4. Delete data karyawan");
		System.out.println("5. Exit");

		// Choose
		Integer choice = choose(1, 5);

		switch(choice){
			case 1:
				insertDataKaryawan();
				break;
			case 2:
				viewDataKaryawan();
				break;
			case 3:
				updateDataKaryawan();
				break;
			case 4:
				deleteDataKaryawan();
				break;
			case 5:
				System.exit(0);
				break;
		}
	}

	// Menu Functions //
	private static void insertDataKaryawan(){

		// Clear screen //
		clearScreen();

		// Define variables
		String tempNamaKaryawan = "";
		String tempJenisKelamin = "";
		String tempJabatan = "";
		Double tempGaji;
		// Integer tempGaji;
		String tempIDKaryawan = "";
		char dash = '-';
		boolean idKaryawanUnique = false;

		// Input nama karyawan
		do{
			System.out.print("Input nama karyawan [>= 3]: ");
			tempNamaKaryawan = scan.nextLine();

			// Reset if doesn't fit requirements
			if(tempNamaKaryawan.length() < 3){
				tempNamaKaryawan = "";
			}
		} while(tempNamaKaryawan.length() < 3);

		// Input jenis kelamin
		do{
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			tempJenisKelamin = scan.nextLine();

			// Reset if doesn't fit requirements
			if(!tempJenisKelamin.equals("Laki-laki") && !tempJenisKelamin.equals("Perempuan")){
				tempJenisKelamin = "";
			}
		} while(!tempJenisKelamin.equals("Laki-laki") && !tempJenisKelamin.equals("Perempuan"));

		// Input jabatan
		do{
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			tempJabatan = scan.nextLine(); 

			// Reset if doesn't fit requirements
			if(!tempJabatan.equals("Manager") && !tempJabatan.equals("Supervisor") && !tempJabatan.equals("Admin")){
				tempJabatan = "";
			}
		} while(!tempJabatan.equals("Manager") && !tempJabatan.equals("Supervisor") && !tempJabatan.equals("Admin"));

		// Set gaji
		if(tempJabatan.equals("Manager")){
			tempGaji = 8000000.0;
		} else if(tempJabatan.equals("Supervisor")){
			tempGaji = 6000000.0;
		} else{
			tempGaji = 4000000.0;
		}

		// While id karyawan is not unique, keep generating IDs
		do{
			// Generate 2 random alphabets
			StringBuilder stringBuild = new StringBuilder();
			char randomizedCharacter;

			// While amount of letters is smaller than 2, keep generating random alphabets
			for(Integer letterNum = 1; letterNum <= 2; letterNum++){

				randomizedCharacter = (char)(random.nextInt(26) + 'A');
				stringBuild.append(randomizedCharacter);
			}

			// Append '-' to ID Karyawan
			stringBuild.append(dash);

			// While amount of numbers is smaller than 4, keep generating numbers
			Integer randomNumber;
			String randomNumberString = "";
			
			for(Integer numberNum = 1; numberNum <= 4; numberNum++){

				randomNumber = random.nextInt(10);

				if(numberNum == 1){
					randomNumberString = randomNumber.toString();
				} else{
					randomNumberString = randomNumberString + randomNumber.toString();
				}
			}

			// Append to string builder
			stringBuild.append(randomNumberString);

			// Set to temporary id variable
			tempIDKaryawan = stringBuild.toString();

			// // Test print
			// System.out.println(tempIDKaryawan);

			// Check uniqueness
			if(amountOfKaryawans == 0){
				idKaryawanUnique = true;
				break;
			} else{
				
				// Iterate all receptionist data
				for(Integer karyawanIndex = 0; karyawanIndex < listKaryawan.size(); karyawanIndex++){
					
					// If unique, set boolean to true
					if(listKaryawan.get(karyawanIndex).getIdKaryawan().equals(tempIDKaryawan)){
						break;
					} else{
						idKaryawanUnique = true;
						break;
					}
				}
			}

		} while(idKaryawanUnique == false);

		// Construct new karyawan and put in vector
		Karyawan newKaryawan = new Karyawan(tempNamaKaryawan, tempJenisKelamin, tempJabatan, tempGaji, tempIDKaryawan);
		listKaryawan.add(newKaryawan);
		ascendingListKaryawan.add(newKaryawan);
		bubbleSortAscending(ascendingListKaryawan);

		// Update number of karyawan
		amountOfKaryawans = listKaryawan.size();

		// Make vector for id karyawan who got a bonus
		Vector<String> bonusForID = new Vector<String>();
		Enumeration enumerateBonusID = bonusForID.elements();

		// Update amount of jabatan and raises
		if(tempJabatan.equals("Manager")){
			amountOfManagers = amountOfManagers + 1;
			
			if(amountOfManagers - 1 != 0){
				if((amountOfManagers - 1) % 3 == 0){

					Integer managerIndex = 1;
					for(Integer karyawanIndex = 0; karyawanIndex < listKaryawan.size(); karyawanIndex++){
	
						if(listKaryawan.get(karyawanIndex).getJabatan().equals("Manager")){
							listKaryawan.get(karyawanIndex).gaji = listKaryawan.get(karyawanIndex).gaji + (0.1 * listKaryawan.get(karyawanIndex).gaji);
							bonusForID.add(listKaryawan.get(karyawanIndex).idKaryawan);
							managerIndex = managerIndex + 1;
						}
	
						if(managerIndex > amountOfManagers - 1){
							break;
						}
					}
				}
			}

		} else if(tempJabatan.equals("Supervisor")){
			amountOfSupervisors = amountOfSupervisors + 1;

			if(amountOfSupervisors - 1 != 0){
				if((amountOfSupervisors - 1) % 3 == 0){

					Integer supervisorIndex = 1;
					for(Integer karyawanIndex = 0; karyawanIndex < listKaryawan.size(); karyawanIndex++){
	
						if(listKaryawan.get(karyawanIndex).getJabatan().equals("Supervisor")){
							listKaryawan.get(karyawanIndex).gaji = listKaryawan.get(karyawanIndex).gaji + (0.075 * listKaryawan.get(karyawanIndex).gaji);
							bonusForID.add(listKaryawan.get(karyawanIndex).idKaryawan);
							supervisorIndex = supervisorIndex + 1;
						}
	
						if(supervisorIndex > amountOfSupervisors - 1){
							break;
						}
					}
				}
			}

		} else{
			amountOfAdmins = amountOfAdmins + 1;

			if(amountOfAdmins - 1 != 0){
				if((amountOfAdmins - 1) % 3 == 0){

					Integer adminIndex = 1;
					for(Integer karyawanIndex = 0; karyawanIndex < listKaryawan.size(); karyawanIndex++){
	
						if(listKaryawan.get(karyawanIndex).getJabatan().equals("Admin")){
							listKaryawan.get(karyawanIndex).gaji = listKaryawan.get(karyawanIndex).gaji + (0.05 * listKaryawan.get(karyawanIndex).gaji);
							bonusForID.add(listKaryawan.get(karyawanIndex).idKaryawan);
							adminIndex = adminIndex + 1;
						}
	
						if(adminIndex > amountOfAdmins - 1){
							break;
						}
					}
				}
			}
		}

		// // Test print enumeration
		// while(enumerateBonusID.hasMoreElements()){
		// 	System.out.print(enumerateBonusID.nextElement() + " ");
		// }

		// Reset id karyawan uniqueness
		idKaryawanUnique = false;

		// Display success message
		System.out.println("Berhasil menambahkan karyawan dengan id " + tempIDKaryawan);

		// Fix Display id for those who got a bonus
		if(tempJabatan.equals("Manager")){
			
			if(amountOfManagers - 1 > 0){
				if((amountOfManagers - 1) % 3 == 0){
					System.out.print("Bonus sebesar 10% telah diberikan kepada karyawan dengan id ");
	
					while(enumerateBonusID.hasMoreElements()){
						System.out.print(enumerateBonusID.nextElement() + " ");
					}
					System.out.println();
				}
			}
		} else if(tempJabatan.equals("Supervisor")){

			if(amountOfSupervisors - 1 > 0){
				if((amountOfSupervisors - 1) % 3 == 0){
					System.out.print("Bonus sebesar 7.5% telah diberikan kepada karyawan dengan id ");
	
					while(enumerateBonusID.hasMoreElements()){
						System.out.print(enumerateBonusID.nextElement() + " ");
					}
					System.out.println();
				}
			}
		} else{
			
			if(amountOfAdmins - 1 > 0){
				if((amountOfAdmins - 1) % 3 == 0){
					System.out.print("Bonus sebesar 5% telah diberikan kepada karyawan dengan id ");
	
					while(enumerateBonusID.hasMoreElements()){
						System.out.print(enumerateBonusID.nextElement() + " ");
					}
					System.out.println();
				}
			}
		}

		promptEnterKey();
		clearScreen();
		mainMenu();
	}

	private static void viewDataKaryawan(){

		// Clear Screen
		clearScreen();

		// // Copy all karyawan in original vector and sort
		// for(int karyawanIndex = 0; karyawanIndex < listKaryawan.size(); karyawanIndex++){
		// 	Karyawan copyKaryawan = listKaryawan.get(karyawanIndex);
		// 	ascendingListKaryawan.add(copyKaryawan);
		// }
		// bubbleSortAscending(ascendingListKaryawan);

		// Display list
		System.out.println("No	Kode Karyawan	Nama Karyawan	Jenis Kelamin	Jabatan		Gaji Karyawan");
		for(Integer karyawanIndex = 0; karyawanIndex < ascendingListKaryawan.size(); karyawanIndex++){
			System.out.printf("%d 	%s		%s		%s	%s		%.0f", karyawanIndex + 1, ascendingListKaryawan.get(karyawanIndex).getIdKaryawan(), ascendingListKaryawan.get(karyawanIndex).getNama(), ascendingListKaryawan.get(karyawanIndex).getJenisKelamin(), ascendingListKaryawan.get(karyawanIndex).getJabatan(), ascendingListKaryawan.get(karyawanIndex).getGaji());
			System.out.println();
		}

		promptEnterKey();
		clearScreen();
		mainMenu();
	}

	private static void updateDataKaryawan(){

		// Clear Screen
		clearScreen();

		// Check if there are employees
		if(amountOfKaryawans == 0){
			System.out.println("There are no existing employees!");
			promptEnterKey();
			mainMenu();
		}

		// Display list
		printSortedList();

		// Define variables
		String tempNamaKaryawan = "";
		String tempJenisKelamin = "";
		String tempJabatan = "";
		String currentidKaryawan = "";
		String currentJabatan = "";
		Integer orderToEdit = -1;
		Integer indexToEditOriginalList = -1;

		// Input index to edit
		do{
			System.out.print("Input nomor karyawan yang ingin diupdate: ");
			orderToEdit = scan.nextInt(); scan.nextLine();

		} while(orderToEdit < 1 || orderToEdit > listKaryawan.size());


		// Input nama karyawan
		do{
			System.out.print("Input nama karyawan [>= 3]: ");
			tempNamaKaryawan = scan.nextLine();

			// Reset if doesn't fit requirements
			char firstChar = tempNamaKaryawan.charAt(0);

			if(firstChar == '0'){
				break;
			}

			if(tempNamaKaryawan.length() < 3){
				tempNamaKaryawan = "";
			}
		} while(tempNamaKaryawan.length() < 3);

		// Input jenis kelamin
		do{
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			tempJenisKelamin = scan.nextLine();

			// Reset if doesn't fit requirements
			char firstChar = tempJenisKelamin.charAt(0);

			if(firstChar == '0'){
				break;
			}

			if(!tempJenisKelamin.equals("Laki-laki") && !tempJenisKelamin.equals("Perempuan")){
				tempJenisKelamin = "";
			}
		} while(!tempJenisKelamin.equals("Laki-laki") && !tempJenisKelamin.equals("Perempuan"));

		// Input jabatan
		do{
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			tempJabatan = scan.nextLine(); 

			// Reset if doesn't fit requirements
			char firstChar = tempJabatan.charAt(0);

			if(firstChar == '0'){
				break;
			}

			if(!tempJabatan.equals("Manager") && !tempJabatan.equals("Supervisor") && !tempJabatan.equals("Admin")){
				tempJabatan = "";
			}
		} while(!tempJabatan.equals("Manager") && !tempJabatan.equals("Supervisor") && !tempJabatan.equals("Admin"));

		// Set current id karyawan and current jabatan
		Integer indexToEdit = orderToEdit - 1;
		currentidKaryawan = ascendingListKaryawan.get(indexToEdit).idKaryawan;
		currentJabatan = ascendingListKaryawan.get(indexToEdit).jabatan;

		// Find in original list
		String ascendingListName = ascendingListKaryawan.get(indexToEdit).nama;
		for(Integer karyawanIndex = 0; karyawanIndex < listKaryawan.size(); karyawanIndex++){

			if(listKaryawan.get(karyawanIndex).getNama().equals(ascendingListName)){
				indexToEditOriginalList = karyawanIndex;
			}
		}

		// Set new data
		if(!tempNamaKaryawan.equals("0")){
			ascendingListKaryawan.get(indexToEdit).nama = tempNamaKaryawan;
			listKaryawan.get(indexToEditOriginalList).nama = tempNamaKaryawan;
		}

		if(!tempJenisKelamin.equals("0")){
			ascendingListKaryawan.get(indexToEdit).jenisKelamin = tempJenisKelamin;
			listKaryawan.get(indexToEditOriginalList).jenisKelamin = tempJenisKelamin;
		}

		if(!tempJabatan.equals("0")){

			// Change gaji
			if(tempJabatan.equals("Manager")){
				ascendingListKaryawan.get(indexToEdit).setGaji(8000000.0);
				listKaryawan.get(indexToEditOriginalList).setGaji(8000000.0);
			} else if(tempJabatan.equals("Supervisor")){
				ascendingListKaryawan.get(indexToEdit).setGaji(6000000.0);
				listKaryawan.get(indexToEditOriginalList).setGaji(6000000.0);
			} else{
				ascendingListKaryawan.get(indexToEdit).setGaji(4000000.0);
				listKaryawan.get(indexToEditOriginalList).setGaji(4000000.0);
			}

			// Update amount of positions
			if(currentJabatan.equals("Manager")){
				amountOfManagers = amountOfManagers - 1;
			} else if(currentJabatan.equals("Supervisor")){
				amountOfSupervisors = amountOfSupervisors - 1;
			} else{
				amountOfAdmins = amountOfAdmins - 1;
			}

			if(tempJabatan.equals("Manager")){
				amountOfManagers = amountOfManagers + 1;
			} else if(tempJabatan.equals("Supervisor")){
				amountOfSupervisors = amountOfSupervisors + 1;
			} else{
				amountOfAdmins = amountOfAdmins + 1;
			}

			// Edit employee's jabatan
			ascendingListKaryawan.get(indexToEdit).jabatan = tempJabatan;
			listKaryawan.get(indexToEditOriginalList).jabatan = tempJabatan;
		}

		// Display success message
		System.out.println("Berhasil mengupdate karyawan dengan id " + currentidKaryawan);

		promptEnterKey();
		mainMenu();
		
	}

	private static void deleteDataKaryawan(){

		// Clear screen
		clearScreen();

		// Display message if there are no employees yet
		if(amountOfKaryawans == 0){
			System.out.println("There are no existing employees!");
			promptEnterKey();
			mainMenu();
		}

		// Display sorted list
		printSortedList();

		// Set variables
		String tempidKaryawan = "";
		Integer orderToEdit = -1;

		// Input order to delete
		do{
			System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
			orderToEdit = scan.nextInt(); scan.nextLine();

		} while(orderToEdit < 1 || orderToEdit > listKaryawan.size());

		Integer indexToEdit = orderToEdit - 1;
		Integer indexToEditOriginalList = -1;
		tempidKaryawan = ascendingListKaryawan.get(indexToEdit).getIdKaryawan();

		// Find index in original list
		for(Integer karyawanIndex = 0; karyawanIndex < listKaryawan.size(); karyawanIndex++){

			if(listKaryawan.get(karyawanIndex).getNama().equals(tempidKaryawan)){
				indexToEditOriginalList = karyawanIndex;
			}
		}

		// TODO: Remove from vectors
		ascendingListKaryawan.remove(indexToEdit);
		listKaryawan.remove(indexToEditOriginalList);

		// Display success message
		System.out.println("Karyawan dengan kode " + tempidKaryawan + " berhasil dihapus");
		promptEnterKey();
		mainMenu();
	}

	private static void printSortedList(){

		for(Integer karyawanIndex = 0; karyawanIndex < ascendingListKaryawan.size(); karyawanIndex++){
			System.out.printf("%d 	%s		%s		%s	%s		%.0f", karyawanIndex + 1, ascendingListKaryawan.get(karyawanIndex).getIdKaryawan(), ascendingListKaryawan.get(karyawanIndex).getNama(), ascendingListKaryawan.get(karyawanIndex).getJenisKelamin(), ascendingListKaryawan.get(karyawanIndex).getJabatan(), ascendingListKaryawan.get(karyawanIndex).getGaji());
			System.out.println();
		}
	}

	// Utility Functions //
	private static Integer choose(Integer min, Integer max){

		Integer choice = 0;
		do{
			System.out.print(">> ");
			choice = scan.nextInt();
			scan.nextLine();

			// if(choice >= min && choice <= max){
			// 	clearScreen();
			// }
		} while(choice < min || choice > max);

		return choice;
	}

	private static void bubbleSortAscending(Vector<Karyawan> list){

		for(Integer passing = 0; passing < list.size(); passing++){
			for(Integer index = 0; index < list.size() - 1; index++){

				if(list.get(index).getNama().compareTo(list.get(index + 1).getNama()) > 0){

					Karyawan temp = list.get(index);
					list.set(index, list.get(index + 1));
					list.set(index + 1, temp);
				}
			}
		}

	}

	public static void clearScreen(){
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}

	private static void promptEnterKey(){
		System.out.print("ENTER to return");
		scan.nextLine();
	}
}
