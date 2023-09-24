import java.util.ArrayList;
import java.util.Collections;

public class CityTrip {
    public static int chooseBestSum(int maxDistance, int numCities, ArrayList<Integer> distances) {
        // Проверяем, что количество городов, которые нужно посетить, не больше, чем количество городов в списке расстояний
        if (numCities > distances.size()) {
            return -1;
        }

        // Создаем список всех возможных комбинаций городов, которые можно посетить
        ArrayList<ArrayList<Integer>> cityCombinations = new ArrayList<>();
        for (int i = 0; i < distances.size(); i++) {
            for (int j = i + 1; j < distances.size(); j++) {
                for (int k = j + 1; k < distances.size(); k++) {
                    ArrayList<Integer> combination = new ArrayList<>();
                    combination.add(distances.get(i));
                    combination.add(distances.get(j));
                    combination.add(distances.get(k));
                    cityCombinations.add(combination);
                }
            }
        }

        // Сортируем комбинации городов по возрастанию расстояния между ними
        Collections.sort(cityCombinations, (a, b) -> Integer.compare(a.stream().mapToInt(Integer::intValue).sum(), b.stream().mapToInt(Integer::intValue).sum()));

        // Ищем максимальное расстояние, которое можно проехать, посетив numCities городов
        int maxSum = -1;
        for (ArrayList<Integer> combination : cityCombinations) {
            int sum = combination.stream().mapToInt(Integer::intValue).sum();
            if (sum <= maxDistance) {
                maxSum = sum;
            } else {
                break;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        ArrayList<Integer> distances = new ArrayList<>();
        distances.add(50);
        distances.add(55);
        distances.add(56);
        distances.add(57);
        distances.add(58);

        int maxDistance = 163;
        int numCities = 3;

        int result = chooseBestSum(maxDistance, numCities, distances);
        System.out.println(result);
    }
}