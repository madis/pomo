(ns pomo.graphql
  (:require [district.ui.graphql]
            [mount.core :as mount :refer-macros [defstate]]))

(def schema "
   scalar Date
   scalar Keyword

   type Query {
     sanity: Boolean
     user(user_id: ID): User
     searchItems(keyword: String, item_status: Keyword): [Item]
   }

   type User {
     user_id: ID
     user_address: String
     user_registeredOn: Date
     user_premiumMember_: Boolean
     user_cartItems: [CartItem]
   }

   type CartItem {
     cartItem_item: Item
     cartItem_quantity: Int
   }

   type Item {
     item_id: ID
     item_title: String
     item_description: String
     item_status: Keyword
     item_price: Float
   }
")


(-> (mount/with-args {:graphql {:schema schema :url "http://localhost:4567"}}))
