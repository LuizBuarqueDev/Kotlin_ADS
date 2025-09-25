import binascii
import numpy as np
from difflib import SequenceMatcher
from sklearn.metrics.pairwise import cosine_similarity

def hex_to_bits(hex_str):
    """Converte string HEX para vetor binário"""
    bin_str = bin(int(hex_str, 16))[2:].zfill(len(hex_str) * 4)
    return np.array([int(b) for b in bin_str])

def jaccard_similarity(a, b):
    """Similaridade de Jaccard entre dois vetores binários"""
    inter = np.logical_and(a, b).sum()
    union = np.logical_or(a, b).sum()
    return inter / union if union != 0 else 0

def hamming_similarity(a, b):
    """Similaridade baseada em distância de Hamming"""
    min_len = min(len(a), len(b))
    dist = np.sum(a[:min_len] != b[:min_len])
    return 1 - (dist / min_len)

def cosine_sim(a, b):
    """Similaridade do cosseno"""
    min_len = min(len(a), len(b))
    return cosine_similarity([a[:min_len]], [b[:min_len]])[0][0]

def sequence_match_ratio(hex1, hex2):
    """Similaridade de subsequência (como diff inteligente)"""
    return SequenceMatcher(None, hex1, hex2).ratio()

def fingerprint_similarity(hex1, hex2):
    bits1 = hex_to_bits(hex1)
    bits2 = hex_to_bits(hex2)

    sims = {
        "Jaccard": jaccard_similarity(bits1, bits2),
        "Hamming": hamming_similarity(bits1, bits2),
        "Cosine": cosine_sim(bits1, bits2),
        "SeqMatch": sequence_match_ratio(hex1, hex2)
    }

    # Combina as métricas em uma média ponderada
    final_score = (
        sims["Jaccard"] * 0.25 +
        sims["Hamming"] * 0.25 +
        sims["Cosine"] * 0.25 +
        sims["SeqMatch"] * 0.25
    )

    return sims, final_score


# === Exemplo de uso ===
hex_template1 = "03035C110001200183000000000000..."  # seu HEX 1
hex_template2 = "2C21DEBE4DA3DFFE5729217E60BDA..."  # outro HEX para comparar

sims, score = fingerprint_similarity(hex_template1, hex_template2)

print("Similaridades detalhadas:", sims)
print("Score final:", score)
