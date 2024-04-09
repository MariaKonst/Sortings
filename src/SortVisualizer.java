import javax.swing.*;
import java.awt.event.ActionEvent;

public class SortVisualizer extends JFrame {
    private final JComboBox<String> sortAlgorithmComboBox;
    private final JButton sortButton;
    private final SortPanel sortPanel;

    SortVisualizer(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(800, 600);

        //combobox
        sortAlgorithmComboBox = new JComboBox<>(new String[]{
                "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort", "Heap Sort"
        });
        sortAlgorithmComboBox.setBounds(50,50,500,50);
        add(sortAlgorithmComboBox);


        //btn
        sortButton = new JButton("Sort");
        sortButton.addActionListener(this::sortButtonActionPerformed);
        sortButton.setBounds(600,50,100,50);
        add(sortButton);

        //sortpanel
        sortPanel = new SortPanel();
        sortPanel.setBounds(0,100,800,500);
          add(sortPanel);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortVisualizer frame = new SortVisualizer();

            frame.setTitle("Sortings Visiualizer");
            frame.setVisible(true);
        });
    }

    public void sortButtonActionPerformed(ActionEvent e) {
        String selectedSortAlgorithm = (String) sortAlgorithmComboBox.getSelectedItem();
        switch (selectedSortAlgorithm) {
            case "Bubble Sort":
                bubbleSort();
                break;
            case "Selection Sort":
                selectionSort();
                break;
            case "Insertion Sort":
                insertionSort();
                break;
            case "Merge Sort":
                mergeSort(0, sortPanel.getGraphicsElements().length - 1);
                break;
            case "Quick Sort":
                quickSort(0, sortPanel.getGraphicsElements().length - 1);
                break;
            case "Heap Sort":
                heapSort();
                break;
        }
        repaint();
    }


    private void bubbleSort() {
        for (int i = 0; i < sortPanel.getGraphicsElements().length - 1; i++) {
            for (int j = 0; j < sortPanel.getGraphicsElements().length - i - 1; j++) {
                if (sortPanel.getGraphicsElements()[j].getSize() > sortPanel.getGraphicsElements()[j + 1].getSize()) {
                    swap(j, j + 1);
                }

            }
        }
sortPanel.removeAll();

        sortPanel.repaint();
    }

    private void selectionSort() {
        for (int i = 0; i < sortPanel.getGraphicsElements().length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortPanel.getGraphicsElements().length; j++) {
                if (sortPanel.getGraphicsElements()[j].getSize() < sortPanel.getGraphicsElements()[minIndex].getSize()) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(i, minIndex);
            }
        }
    }

    private void insertionSort() {
        for (int i = 1; i < sortPanel.getGraphicsElements().length; i++) {
            GraphicsElement key = sortPanel.getGraphicsElements()[i];
            int j = i - 1;
            while (j >= 0 && sortPanel.getGraphicsElements()[j].getSize() > key.getSize()) {
                sortPanel.getGraphicsElements()[j + 1] = sortPanel.getGraphicsElements()[j];
                j = j - 1;
            }
            sortPanel.getGraphicsElements()[j + 1] = key;
        }
    }

    private void mergeSort(int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            merge(left, mid, right);
        }
    }


    private  void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        GraphicsElement[] leftArray = new GraphicsElement[n1];
        GraphicsElement[] rightArray = new GraphicsElement[n2];

        for (int i = 0; i < n1; ++i)
            leftArray[i] = sortPanel.getGraphicsElements()[left + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = sortPanel.getGraphicsElements()[mid + 1 + j];

        int i = 0, j = 0;

        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].getSize() <= rightArray[j].getSize()) {
                sortPanel.getGraphicsElements()[k] = leftArray[i];
                i++;
            } else {
                sortPanel.getGraphicsElements()[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            sortPanel.getGraphicsElements()[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            sortPanel.getGraphicsElements()[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);

            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        GraphicsElement pivot = sortPanel.getGraphicsElements()[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (sortPanel.getGraphicsElements()[j].getSize() < pivot.getSize()) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }

    private void swap(int i, int j) {
        GraphicsElement temp = sortPanel.getGraphicsElements()[i];
        sortPanel.getGraphicsElements()[i] = sortPanel.getGraphicsElements()[j];
        sortPanel.getGraphicsElements()[j] = temp;
    }

    private void heapSort() {
        for (int i = sortPanel.getGraphicsElements().length / 2 - 1; i >= 0; i--)
            heapify(sortPanel.getGraphicsElements().length, i);

        for (int i = sortPanel.getGraphicsElements().length - 1; i >= 0; i--) {
            swap(0, i);
            heapify(i, 0);
        }
    }

    private void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && sortPanel.getGraphicsElements()[left].getSize() > sortPanel.getGraphicsElements()[largest].getSize())
            largest = left;

        if (right < n && sortPanel.getGraphicsElements()[right].getSize() > sortPanel.getGraphicsElements()[largest].getSize())
            largest = right;

        if (largest != i) {
            swap(i, largest);
            heapify(n, largest);
        }
    }
}

