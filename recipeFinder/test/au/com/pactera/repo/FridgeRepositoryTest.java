package au.com.pactera.repo;

import au.com.pactera.model.*;
import com.google.common.collect.Lists;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FridgeRepositoryTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("d/M/yyyy");

    private static final List<FridgeItem> ITEMS_ONE = Lists.newArrayList(
            new FridgeItem("bread", 8, Unit.SLICES, FORMATTER.parseLocalDate("29/03/2015")),
            new FridgeItem("cheese", 10, Unit.SLICES, FORMATTER.parseLocalDate("13/04/2015")));

    private static final List<FridgeItem> ITEMS_THREE = Lists.newArrayList(
            new FridgeItem("guava", 8300, Unit.GRAMS, FORMATTER.parseLocalDate("29/03/2015")),
            new FridgeItem("rice", 500, Unit.ML, FORMATTER.parseLocalDate("29/04/2015")),
            new FridgeItem("rice", 700, Unit.ML, FORMATTER.parseLocalDate("22/04/2015")));

    private static final List<FridgeItem> ITEMS_TWO = Lists.newArrayList(
            new FridgeItem("guava", 8300, Unit.GRAMS, FORMATTER.parseLocalDate("29/03/2015")),
            new FridgeItem("rice", 500, Unit.ML, FORMATTER.parseLocalDate("29/04/2015")));
    
    private FridgeRepository repository;

    @BeforeMethod
    public void init() {
        repository = FridgeRepository.instance();
    }

    @Test
    public void update_shouldRemoveItems_BeforeAddingNewOnes() {
        repository.update(ITEMS_ONE);
        List<FridgeItem> itemsOne = repository.get();
        assertThat(itemsOne).hasSize(2).containsAll(ITEMS_ONE);

        repository.update(ITEMS_TWO);
        List<FridgeItem> itemsTwo = repository.get();
        assertThat(itemsTwo).hasSize(2).containsAll(ITEMS_TWO);
    }

    @Test
    public void update_shouldOverrideDuplicatedItems() {
        repository.update(ITEMS_THREE);
        List<FridgeItem> items = repository.get();
        assertThat(items).hasSize(2).contains(ITEMS_THREE.get(0)).contains(ITEMS_THREE.get(2));
    }

    @Test
    public void removeAll_shouldClearTheRepository() {
        repository.update(ITEMS_ONE);
        assertThat(repository.isEmpty()).isFalse();
        assertThat(repository.get()).hasSize(2);

        repository.removeAll();
        assertThat(repository.isEmpty()).isTrue();
        assertThat(repository.get()).isEmpty();
    }
}
