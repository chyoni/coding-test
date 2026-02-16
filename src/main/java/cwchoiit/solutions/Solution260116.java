package cwchoiit.solutions;

import java.util.Map;

/**
 * [문제: 숫자 문자열과 영단어]
 * 네오와 프로도가 숫자 놀이를 하고 있습니다. 일부 숫자가 영단어로 바뀌어 있는 문자열이 주어질 때, 이를 원래의 숫자로 차례대로 바꾼 정수를 반환하는 함수를 작성해 보세요.
 * 예시: "one4seveneight" -> 1478
 * 참고:
 * 0: zero, 1: one, 2: two, 3: three, 4: four
 * 5: five, 6: six, 7: seven, 8: eight, 9: nine
 */
public class Solution260116 {

    public static void main(String[] args) {
        String question = "one4seveneight";
        int answer = solutionGemini2(question);
        System.out.println("answer = " + answer);
    }

    private static int solutionMe(String question) {
        String stringAnswer = question
                .replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9")
                .replace("zero", "0");

        return Integer.parseInt(stringAnswer);
    }

    private static int solutionGemini(String question) {
        String[] words = {
                "zero",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine",
        };

        for (int i = 0; i < words.length; i++) {
            question = question.replace(words[i], String.valueOf(i));
        }

        return Integer.parseInt(question);
    }

    /**
     * 이 방식이 더 좋은 이유는, 우선 question을 딱 한번 순회한다. 위 방식은 replace를 10번 호출하는데 이는 문자열 전체를 10번 순회하는 것이다.
     * 또한, 메모리 측면에서도 replace는 매번 새로운 문자열 객체를 힙 메모리에 생성한다. 반면, StringBuilder는 기존 버퍼를 사용하므로 가비지 컬렉터의 부담을 줄여준다.
     * 확장성을 고려했을때도, 나중에 변환해야 할 단어가 100개로 늘어나도 이 로직은 여전히 문자열을 한 번만 읽으면 끝난다.
     */
    private static int solutionGemini2(String question) {
        final Map<String, String> WORD_MAP = Map.of(
                "zero", "0",
                "one", "1",
                "two", "2",
                "three", "3",
                "four", "4",
                "five", "5",
                "six", "6",
                "seven", "7",
                "eight", "8",
                "nine", "9"
        );

        StringBuilder answer = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (char c : question.toCharArray()) {
            if (Character.isDigit(c)) {
                answer.append(c);
            } else {
                word.append(c);
                if (WORD_MAP.containsKey(word.toString())) {
                    answer.append(WORD_MAP.get(word.toString()));
                    word.setLength(0);
                }
            }
        }

        return Integer.parseInt(answer.toString());
    }
}
