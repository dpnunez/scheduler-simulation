import sys

# Verifica se o número correto de argumentos foi fornecido
if len(sys.argv) < 2:
    print("Uso: python generate_charts.py <parametro>")
    sys.exit(1)


# slice the first element of the list
file_paths = sys.argv[1:]

print(file_paths)


# para cada item dentro de file_paths, quero ler o arquivo e gerar um gráfico


for file_path in file_paths:
    # Ler o arquivo
    max_last_number = 0  # Inicializa o maior número como 0
    with open(file_path, 'r') as file:
        data = file.readlines()
        
        current_processor = None
        for line in data:
            line = line.strip()
            if line.startswith('Processador'):
                current_processor = line
            else:
                parts = line.split(';')
                if len(parts) < 3:
                    continue
                end_time = int(parts[2])
                max_last_number = max(max_last_number, end_time)
    
    print(f'{file_path}: {max_last_number}')
