import sys
import json
import matplotlib.pyplot as plt
import numpy as np

# Verifica se o número correto de argumentos foi fornecido
if len(sys.argv) < 2:
    print("Uso: python generate_charts.py <parametro>")
    sys.exit(1)


# slice the first element of the list
file_paths = sys.argv[1:]

print(file_paths)


# para cada item dentro de file_paths, quero ler o arquivo e gerar um gráfico

parsed_data_vector = []

for file_path in file_paths:
    # Ler o arquivo
    max_last_number = 0  # Inicializa o maior número como 0
    # extract file data, the file name follow the pattern: "./results_{process_type}_{algorithm}_{number_processors}.txt"
    process_type = file_path.split('_')[1]
    algorithm = file_path.split('_')[2]
    number_processors = file_path.split('_')[3].split('.')[0]
    

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
    
    # create a dictionary with the data

    parsed_data = {
        'process_type': process_type,
        'algorithm': algorithm,
        'number_processors': number_processors,
        'max_last_number': max_last_number
    }

    parsed_data_vector.append(parsed_data)
    print(f'Process type: {process_type}, Algorithm: {algorithm}, Number of processors: {number_processors}, Max last number: {max_last_number}')


# print(parsed_data_vector)

# Group vector by process_type
grouped_by_process_type = {}
for parsed_data in parsed_data_vector:
    process_type = parsed_data['process_type']
    if process_type not in grouped_by_process_type:
        grouped_by_process_type[process_type] = []
    grouped_by_process_type[process_type].append(parsed_data)


for key, values in grouped_by_process_type.items():
    processors = sorted(set(item['number_processors'] for item in values))
    algorithms = sorted(set(item['algorithm'] for item in values))

    # Create a dictionary to hold max_last_numbers grouped by number_processors and algorithm
    grouped_data = {processor: {algorithm: 0 for algorithm in algorithms} for processor in processors}

    for item in values:
        grouped_data[item['number_processors']][item['algorithm']] = item['max_last_number']

    bar_width = 0.35
    index = np.arange(len(processors))

    fig, ax = plt.subplots(figsize=(10, 6))

    for i, algorithm in enumerate(algorithms):
        max_last_numbers = [grouped_data[processor][algorithm] for processor in processors]
        bars = ax.bar(index + i * bar_width, max_last_numbers, bar_width, label=algorithm)
        
        # Add text on the bars
        for bar in bars:
            yval = bar.get_height()
            ax.text(
                bar.get_x() + bar.get_width() / 2, 
                yval, 
                f'{int(yval)}', 
                ha='center', 
                va='bottom'
            )

    ax.set_xlabel('Number of CPUs')
    ax.set_ylabel('Time spend (units of time)')
    ax.set_title(f'{key.capitalize()} Process Type')
    ax.set_xticks(index + bar_width / len(algorithms))
    ax.set_xticklabels(processors)
    ax.legend()

    plt.tight_layout()
    plt.savefig(f'{key}.png')
    plt.close()