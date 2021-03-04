(ns pomo.subs
  (:require
   [re-frame.core :as re]))

(re/reg-sub
  :timer-activation
  (fn [db _]
    (:timer-started db)))

(re/reg-sub
  :timer-update
  (fn [db _]
    (:time-now db)))

(re/reg-sub
  :time-left
  :<- [:timer-update]
  (fn [db _]
    (println "time-left Ticking" (:progress db))
    (:progress db)))
