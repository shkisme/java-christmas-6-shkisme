package christmas.controller;

import christmas.model.benefits.Benefits;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final Benefits benefits;

    public Controller(InputView inputView, OutputView outputView, Benefits benefits) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.benefits = benefits;
    }

    public void run() {
        outputView.printStartMessage();
        inputView.readDate();
        inputView.readMenu();

        outputView.printResultMessage();
    }
}
