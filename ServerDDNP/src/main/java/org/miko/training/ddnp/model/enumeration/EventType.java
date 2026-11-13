package org.miko.training.ddnp.model.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EventType {

    DOSSIER_CREATED(1),                    // Досье техники создано С
    DOSSIER_DATA_UPDATED(2),                // Данные досье обновлены
    SELLER_DATA_RECEIVED(3),                // Получены сведения о продавце
    VEHICLE_DATA_RECEIVED(4),               // Получены сведения о технике
    VEHICLE_DOCUMENTS_RECEIVED(5),           // Получены документы на технику

    VEHICLE_REGISTERED(31),                 // регистрация техники в реестре С

    SELLER_CHECK_STARTED(6),                // Проверка продавца начата
    SELLER_CHECK_COMPLETED(7),              // Проверка продавца завершена
    SELLER_CHECK_TIMEOUT(8),                // Истекло время ожидания проверки продавца

    ENCUMBRANCE_CHECK_STARTED(9),            // Проверка обременений начата
    ENCUMBRANCE_CHECK_COMPLETED(10),        // Проверка обременений завершена
    ENCUMBRANCE_CHECK_TIMEOUT(11),          // Истекло время ожидания проверки обременений

    VEHICLE_REGISTRY_CHECK_STARTED(12),     // Проверка учета техники начата
    VEHICLE_REGISTRY_CHECK_COMPLETED(13),   // Проверка учета техники завершена
    VEHICLE_REGISTRY_CHECK_TIMEOUT(14),     // Истекло время ожидания проверки учета техники

    VEHICLE_DATA_VERIFICATION_STARTED(15),  // Сверка данных техники начата
    VEHICLE_DATA_VERIFICATION_COMPLETED(16),// Сверка данных техники завершена  С

    VEHICLE_VALUATION_STARTED(17),          // Оценка техники начата
    VEHICLE_VALUATION_COMPLETED(18),        // Оценка техники завершена     С

    PURCHASE_DECISION_MADE(19),             // Решение о приобретении принято    С
    PURCHASE_APPROVED(20),                  // Приобретение одобрено
    PURCHASE_REJECTED(21),                  // В приобретении отказано

    PURCHASE_CONTRACT_SIGNED(22),           // Договор приобретения подписан
    VEHICLE_PURCHASED(23),                 // Техника приобретена     С

    VEHICLE_RECEIVED(24),                  // Техника принята на склад
    VEHICLE_DIAGNOSTICS_STARTED(25),        // Диагностика техники начата
    VEHICLE_DIAGNOSTICS_COMPLETED(26),      // Диагностика техники завершена

    VEHICLE_REPAIR_STARTED(27),            // Ремонт техники начат
    VEHICLE_REPAIR_COMPLETED(28),          // Ремонт техники завершен

    VEHICLE_LISTED_FOR_SALE(29),           // Объявление о продаже техники опубликовано    С
    VEHICLE_SOLD(30);                      // Техника продана    С

    private final int code;
}
