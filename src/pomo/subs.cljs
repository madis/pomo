(ns pomo.subs
  (:require
   [re-frame.core :as re]))

(re/reg-sub
  :timer-activation
  (fn [db _]
    (:timer-started db)))
