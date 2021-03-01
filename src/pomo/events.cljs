(ns pomo.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx reg-fx inject-cofx trim-v after path] :as re]))

(re/reg-event-fx
 :initialise-db
 (fn [_ _]
   {:db {:the-answer 42
         :timer-started false
         :timer-start (* 25 60)}}))

(re/reg-event-db
  :timer-toggle
  (fn [db [_ timer-started]]
    (assoc db :timer-started (not timer-started))))
