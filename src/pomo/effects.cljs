(ns pomo.effects
  (:require
    [re-frame.core :as re]))

(def interval-ids (atom {}))

(re/reg-fx
  :start-repeat
  (fn [{:keys [:id :event :millis]}]
    (let [interval-id (js/setInterval #(do (re/dispatch [event])
                                           (re/dispatch [:timer-tick (js/Date.)])) millis)]
      (swap! interval-ids assoc id interval-id))))
