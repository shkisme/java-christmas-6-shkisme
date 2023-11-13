package christmas.model.presentation;

import java.util.Optional;

public interface Presentation {

    Optional<String> getPresentationName();

    int getBenefits();
}
