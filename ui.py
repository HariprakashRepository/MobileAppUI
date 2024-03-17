import tkinter as tk

class TransactionPanel(tk.Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.master = master
        self.create_widgets()

    def create_widgets(self):
        self.transaction_number_label = tk.Label(self, text="Transaction Number:")
        self.transaction_number_entry = tk.Entry(self)
        self.transaction_number_label.grid(row=0, column=0, sticky="w")
        self.transaction_number_entry.grid(row=0, column=1, padx=5, pady=5)

        self.transaction_name_label = tk.Label(self, text="Transaction Name:")
        self.transaction_name_entry = tk.Entry(self)
        self.transaction_name_label.grid(row=1, column=0, sticky="w")
        self.transaction_name_entry.grid(row=1, column=1, padx=5, pady=5)

        self.xpath_id_label = tk.Label(self, text="XPath/Element ID:")
        self.xpath_id_entry = tk.Entry(self)
        self.xpath_id_label.grid(row=2, column=0, sticky="w")
        self.xpath_id_entry.grid(row=2, column=1, padx=5, pady=5)

        self.assertion_label = tk.Label(self, text="Assertion:")
        self.assertion_entry = tk.Entry(self)
        self.assertion_label.grid(row=3, column=0, sticky="w")
        self.assertion_entry.grid(row=3, column=1, padx=5, pady=5)

class FPLMobileUI(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("FPL Mobile App")
        self.transaction_panels = []
        self.create_widgets()

    def create_widgets(self):
        self.transaction_panel_container = tk.Frame(self)
        self.transaction_panel_container.pack(padx=10, pady=10)

        self.add_transaction_button = tk.Button(self, text="Add Transaction", command=self.add_transaction_panel)
        self.add_transaction_button.pack(pady=10)

        self.add_transaction_panel()

    def add_transaction_panel(self):
        transaction_panel = TransactionPanel(self.transaction_panel_container)
        transaction_panel.pack(padx=5, pady=5)
        self.transaction_panels.append(transaction_panel)

if __name__ == "__main__":
    app = FPLMobileUI()
    app.mainloop()
