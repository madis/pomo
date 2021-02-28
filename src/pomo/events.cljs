(ns pomo.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx reg-fx inject-cofx trim-v after path]]))

(reg-event-fx
 :initialise-db
 (fn [_ _]
   {:db {:the-answer 42}}))
