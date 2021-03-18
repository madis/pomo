(ns pomo.graphql)

(def schema "
   type Query {
     sanity: Boolean
     user(user_id: ID): User
   }

   type User {
     user_id: ID
     user_address: String
     user_premiumMember: Boolean
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
     item_price: Float
   }
")
