package com.company.aviation.view.airplane;

import com.company.aviation.entity.Airplane;
import com.company.aviation.entity.Airport;
import com.company.aviation.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.InstanceContainer;

import java.util.UUID;
import io.jmix.flowui.view.*;

@Route(value = "airplanes/:id", layout = MainView.class)
@ViewController(id = "aviation_Airplane.detail")
@ViewDescriptor(path = "airplane-detail-view.xml")
@EditedEntityContainer("airplaneDc")
public class AirplaneDetailView extends StandardDetailView<Airplane> {

    @ViewComponent
    private EntityComboBox<Airport> airportField;
    @ViewComponent
    private CollectionLoader<com.company.aviation.entity.Flight> flightsDl;

    @Subscribe
    public void onInit(final InitEvent event) {
        airportField.addValueChangeListener(e -> reloadFlightsForAirport(e.getValue()));
    }

    @Subscribe
    public void onReady(final ReadyEvent event) {
        reloadFlightsForAirport(getEditedEntity().getAirport());
    }

    @Subscribe(id = "airplaneDc", target = Target.DATA_CONTAINER)
    public void onAirplaneDcItemChange(final InstanceContainer.ItemChangeEvent<Airplane> event) {
        reloadFlightsForAirport(event.getItem() != null ? event.getItem().getAirport() : null);
    }

    private void reloadFlightsForAirport(Airport airport) {
        UUID airportId = airport != null ? airport.getId() : null;
        flightsDl.setParameter("airportId", airportId);
        flightsDl.load();
    }
}
