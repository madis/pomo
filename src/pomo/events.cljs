(ns pomo.events
  (:require
   [re-frame.core :refer [reg-event-db reg-event-fx reg-fx inject-cofx trim-v after path] :as re]))

(re/reg-event-fx
 :initialise-db
 (fn [_ _]
   (println "initializing db")
   {:db {:the-answer 42
         :timer-started false
         :progress (* 25 60)}}))

(re/reg-event-fx
  :timer-toggle
  (fn [cofx [_ timer-started]]
    {:db {:timer-started (not timer-started)}
     :start-repeat {:id :timer :event :countdown-tick :millis 1000}}))

(re/reg-event-db
  :timer-tick
  (fn [db [_ new-time]]
    (assoc db :time-now new-time)))

(re/reg-event-db
  :countdown-tick
  (fn [db]
    (println "countdown-tick updating"  (:db db))
    (assoc db :progress (- (:progress db) 1))
    db))
