package com.vitor.cordeiro.teste.quarkus.client.model;

public class GoogleBookSpanelizationSummary {

    private Boolean containsEpubBubbles;
    private Boolean containsImageBubbles;

    public GoogleBookSpanelizationSummary(){}

    public GoogleBookSpanelizationSummary(Boolean containsEpubBubbles, Boolean containsImageBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
        this.containsImageBubbles = containsImageBubbles;
    }

    public Boolean getContainsEpubBubbles() {
        return containsEpubBubbles;
    }

    public void setContainsEpubBubbles(Boolean containsEpubBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
    }

    public Boolean getContainsImageBubbles() {
        return containsImageBubbles;
    }

    public void setContainsImageBubbles(Boolean containsImageBubbles) {
        this.containsImageBubbles = containsImageBubbles;
    }
}
