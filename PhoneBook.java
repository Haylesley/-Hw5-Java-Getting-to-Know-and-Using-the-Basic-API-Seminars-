// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с разными телефонами,
// их необходимо считать, как одного человека с разными телефонами.
// Вывод должен быть отсортирован по убыванию числа телефонов.
// Пример ввода:
// Иванов 234234
// Иванов 32523
// Иванов 5687
// Иванов: 234234, 32523, 5687

// Варианты Map:
// Map<String, ArrayList>
// Map<String, String>

// Пример меню:
// 1. Добавить контакт
// 2. Вывести всех
// 3. Выход

import java.util.*;

public class PhoneBook {
    private Map<String, List<String>> contacts;

    public PhoneBook() {
        contacts = new HashMap<>();
    }

    public void addContact(String name, String phoneNumber) {
        List<String> phoneNumbers = contacts.getOrDefault(name, new ArrayList<>());
        phoneNumbers.add(phoneNumber);
        contacts.put(name, phoneNumbers);
    }

    public void printAllContacts() {
        List<Map.Entry<String, List<String>>> sortedEntries = new ArrayList<>(contacts.entrySet());
        Collections.sort(sortedEntries, (a, b) -> Integer.compare(b.getValue().size(), a.getValue().size()));

        for (Map.Entry<String, List<String>> entry : sortedEntries) {
            String name = entry.getKey();
            List<String> phoneNumbers = entry.getValue();
            Collections.sort(phoneNumbers, Collections.reverseOrder());
            System.out.println(name + ": " + String.join(", ", phoneNumbers));
        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Меню:");
            System.out.println("1. Добавить контакт");
            System.out.println("2. Вывести всех");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите имя: ");
                    String name = scanner.next();
                    System.out.print("Введите номер телефона: ");
                    String phoneNumber = scanner.next();
                    phoneBook.addContact(name, phoneNumber);
                    break;
                case 2:
                    System.out.println("Контакты:");
                    phoneBook.printAllContacts();
                    break;
                case 3:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }

            System.out.println();
        } while (choice != 3);

        scanner.close();
    }
}
