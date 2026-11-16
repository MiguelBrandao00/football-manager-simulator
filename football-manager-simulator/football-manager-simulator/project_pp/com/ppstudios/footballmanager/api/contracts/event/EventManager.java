package com.ppstudios.footballmanager.api.contracts.event;
/**
 * Nome: <Maria Leonor Ferreira Araujo>
 * Número: <8240331>
 * Turma: <LEI T2>
 * Nome: <Miguel Brandao Ferreira>
 * Número: <8240332>
 * Turma: <LEI T2>
 */

/**
 * Classe responsavel por gerir eventos num jogo de futebol.
 * <p>
 * Esta classe permite adicionar, consultar e armazenar eventos,
 * como golos, faltas, cantos, etc. Usa um array dinamico para
 * armazenar ate um numero arbitrario de eventos.
 */
public class EventManager implements IEventManager {
    private static final int MAX_NUMBER_EVENTS = 50;
    private static final int INCREASE_FACTOR = 2;

    private IEvent[] events;
    private int contEvents;

    /**
     * Construtor por omissao. Inicializa o array de eventos com
     * capacidade inicial de 'MAX_NUMBER_EVENTS'.
     */
    public EventManager() {
        this.events = new IEvent[MAX_NUMBER_EVENTS];
        this.contEvents = 0;
    }

    /**
     * Substitui os eventos existentes pelos fornecidos.
     *
     * @param events Array de eventos a ser armazenado.
     */
    public void setEvents(IEvent[] events) {
        this.events = new IEvent[events.length];
        for (int i = 0; i < events.length; i++) {
            this.events[i] = events[i];
        }
        this.contEvents = events.length;
    }

    /**
     * Retorna uma copia do array de eventos.
     *
     * @return Array de eventos.
     */
    @Override
    public IEvent[] getEvents() {
        IEvent[] copy = new IEvent[events.length];
        for (int i = 0; i < events.length; i++) {
            copy[i] = events[i];
        }
        return copy;
    }

    /**
     * Aumenta a capacidade interna do array de eventos
     * em 'INCREASE_FACTOR'.
     */
    private void increaseArray() {
        IEvent[] newEvents = new IEvent[events.length * INCREASE_FACTOR];
        for (int i = 0; i < events.length; i++) {
            newEvents[i] = events[i];
        }
        events = newEvents;
    }

    /**
     * Adiciona um novo evento a lista, caso ainda nao exista.
     * Se o array estiver cheio, aumenta a sua capacidade.
     *
     * @param event adicionado ao array de eventos.
     */
    @Override
    public void addEvent(IEvent event) {
        if (findEvent(event) != -1) {
            return;
        }
        if (contEvents == events.length) {
            increaseArray();
        }
        events[contEvents++] = event;
    }

    /**
     * Verifica se um evento ja existe no array.
     *
     * @param event Evento a verificar.
     * @return Indice do evento se existir, -1 caso contrario.
     */
    private int findEvent(IEvent event) {
        for (int i = 0; i < contEvents; i++) {
            if (events[i].equals(event)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna o numero atual de eventos armazenados.
     *
     * @return Quantidade de eventos.
     */
    @Override
    public int getEventCount() {
        return this.contEvents;
    }
}
