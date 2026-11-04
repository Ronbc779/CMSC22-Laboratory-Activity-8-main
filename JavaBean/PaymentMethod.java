// a way for the customer to pay. pay() collects the payment and returns how much was paid
//Design Pattern: Strategy. Separate algorithm based on the PaymentMethod at runtime.
interface PaymentMethod {
    int pay(int cost);
}
